package com.mars7.mars7_recruit_backend.resume.service;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.common.enums.ErrorCode;
import com.mars7.mars7_recruit_backend.common.exception.BusinessException;
import com.mars7.mars7_recruit_backend.mypage.repository.MypageRepository;
import com.mars7.mars7_recruit_backend.resume.dto.ApplicantDetailResponseDto;
import com.mars7.mars7_recruit_backend.resume.dto.ApplicantListResponseDto;
import com.mars7.mars7_recruit_backend.resume.dto.ResumeRequestDto;
import com.mars7.mars7_recruit_backend.resume.dto.ResumeResponseDto;
import com.mars7.mars7_recruit_backend.resume.entity.ResumeEntity;
import com.mars7.mars7_recruit_backend.resume.repository.ResumeRepository;
import com.mars7.mars7_recruit_backend.recruit.entity.RecruitEntity;
import com.mars7.mars7_recruit_backend.recruit.repository.RecruitRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final MypageRepository mypageRepository;
    private final RecruitRepository recruitRepository;

    public ResumeService(
            @Qualifier("userResumeRepository") ResumeRepository resumeRepository,
            MypageRepository mypageRepository,
            RecruitRepository recruitRepository) {
        this.resumeRepository = resumeRepository;
        this.mypageRepository = mypageRepository;
        this.recruitRepository = recruitRepository;
    }

    /**
     * 지원서 작성
     */
    @Transactional
    public ResumeResponseDto submitResume(ResumeRequestDto request) {
        ResumeEntity resume = ResumeEntity.builder()
                .recruitId(request.getRecruitId())
                .userId(request.getUserId())
                .title(request.getTitle())
                .selfIntroduce(request.getSelfIntroduce())
                .createdAt(LocalDateTime.now())
                .build();

        ResumeEntity savedResume = resumeRepository.save(resume);
        return ResumeResponseDto.from(savedResume);
    }

    /**
     * 지원자 목록 조회 (공고 작성자 본인 확인 로직)
     */
    @Transactional(readOnly = true)
    public List<ApplicantListResponseDto> getApplicantsByRecruitId(Long recruitId, String usersId) {
        // 1. 접속한 유저 정보 조회
        UserEntity currentUser = mypageRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 2. 공고 정보 조회
        RecruitEntity recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RECRUIT_NOT_FOUND));

        // 3. 게시자 본인 여부 확인 (RecruitService와 동일한 로직 적용)
        if (!recruit.getUser().getId().equals(currentUser.getId())) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        List<ResumeEntity> resumes = resumeRepository.findAllByRecruitId(recruitId);

        return resumes.stream().map(resume -> {
            UserEntity applicantUser = mypageRepository.findById(resume.getUserId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

            int age = LocalDateTime.now().getYear() - applicantUser.getBirth().getYear() + 1;
            return ApplicantListResponseDto.of(resume, applicantUser, age);
        }).toList();
    }

    /**
     * 지원자 상세 조회 (공고 작성자 본인 확인 로직)
     */
    @Transactional(readOnly = true)
    public ApplicantDetailResponseDto getApplicantDetail(Long resumeId, String usersId) {
        // 1. 접속한 유저 정보 조회
        UserEntity currentUser = mypageRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 2. 지원서 및 관련 공고 조회
        ResumeEntity resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        RecruitEntity recruit = recruitRepository.findById(resume.getRecruitId())
                .orElseThrow(() -> new BusinessException(ErrorCode.RECRUIT_NOT_FOUND));

        // 3. 게시자 본인 여부 확인
        if (!recruit.getUser().getId().equals(currentUser.getId())) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        UserEntity applicantUser = mypageRepository.findById(resume.getUserId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        int age = LocalDateTime.now().getYear() - applicantUser.getBirth().getYear() + 1;
        return ApplicantDetailResponseDto.of(resume, applicantUser, age);
    }

    /**
     * 내 지원서 목록 조회 (JWT 기준)
     */
    @Transactional(readOnly = true)
    public List<ResumeResponseDto> getMyResumes(String usersId) {
        UserEntity user = mypageRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        List<ResumeEntity> resumes = resumeRepository.findAllByUserId(user.getId());

        return resumes.stream()
                .map(ResumeResponseDto::from)
                .toList();
    }
    /**
     * 내 지원서 상세 조회 (로그인한 유저 본인용)
     */
    @Transactional(readOnly = true)
    public ApplicantDetailResponseDto getMyResumeDetail(Long resumeId, String usersId) {
        // 1. 현재 로그인한 유저 정보 조회
        UserEntity currentUser = mypageRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 2. 지원서 정보 조회
        ResumeEntity resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 3. 본인 확인 검증: 지원서의 작성자 ID와 로그인 유저의 PK 비교
        if (!resume.getUserId().equals(currentUser.getId())) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        // 4. 나이 계산 (현재 연도 - 생년 + 1)
        int age = LocalDateTime.now().getYear() - currentUser.getBirth().getYear() + 1;

        // 5. 요청하신 상세 정보 DTO로 변환하여 반환
        return ApplicantDetailResponseDto.of(resume, currentUser, age);
    }
}