package com.mars7.mars7_recruit_backend.recruit.service;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.auth.repository.UserRepository;
import com.mars7.mars7_recruit_backend.common.enums.ErrorCode;
import com.mars7.mars7_recruit_backend.common.enums.RecruitField;
import com.mars7.mars7_recruit_backend.common.exception.BusinessException;
import com.mars7.mars7_recruit_backend.recruit.dto.*;
import com.mars7.mars7_recruit_backend.recruit.entity.RecruitEntity;
import com.mars7.mars7_recruit_backend.recruit.repository.RecruitRepository;
import com.mars7.mars7_recruit_backend.recruit.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    /**
     * 1. 동아리 검색 (키워드로 제목/내용 검색)
     */
    public List<RecruitListResponseDto> searchRecruits(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return recruitRepository.findAllByOrderByCreatedAtDesc()
                    .stream()
                    .map(RecruitListResponseDto::from)
                    .collect(Collectors.toList());
        }

        return recruitRepository.searchByKeyword(keyword.trim())
                .stream()
                .map(RecruitListResponseDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 2. 동아리 분야별 조회
     */
    public List<RecruitListResponseDto> getRecruitsByField(RecruitField field) {
        if (field == null || field == RecruitField.ALL) {
            return recruitRepository.findAllByOrderByCreatedAtDesc()
                    .stream()
                    .map(RecruitListResponseDto::from)
                    .collect(Collectors.toList());
        }

        return recruitRepository.findByFieldOrderByCreatedAtDesc(field)
                .stream()
                .map(RecruitListResponseDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 3. 동아리 모집글 작성
     */
    @Transactional
    public RecruitResponseDto createRecruit(String usersId, RecruitCreateRequestDto requestDto) {
        UserEntity user = userRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        RecruitEntity recruit = RecruitEntity.builder()
                .user(user)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .field(requestDto.getField())
                .gender(requestDto.getGender())
                .people(requestDto.getPeople())
                .startDate(requestDto.getStartDate())
                .dueDate(requestDto.getDueDate())
                .resultDate(requestDto.getResultDate())
                .posterImage(requestDto.getPosterImage())
                .viewCount(0)
                .build();

        RecruitEntity savedRecruit = recruitRepository.save(recruit);
        return RecruitResponseDto.from(savedRecruit);
    }

    /**
     * 4. 동아리 모집글 상세 조회 (지원자용)
     */
    @Transactional
    public RecruitResponseDto getRecruitDetail(Long recruitId) {
        RecruitEntity recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RECRUIT_NOT_FOUND));

        recruit.increaseViewCount();
        return RecruitResponseDto.from(recruit);
    }

    /**
     * 5. 동아리 모집글 상세 조회 (게시자용 - 지원자 수 포함)
     */
    @Transactional
    public RecruitOwnerResponseDto getRecruitDetailForOwner(String usersId, Long recruitId) {
        UserEntity user = userRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        RecruitEntity recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RECRUIT_NOT_FOUND));

        // 게시자 본인인지 확인
        if (!recruit.getUser().getId().equals(user.getId())) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        Integer applicantCount = resumeRepository.countByRecruitRecruitId(recruitId);
        List<ApplicantInfoDto> applicants = resumeRepository.findByRecruitRecruitIdOrderByCreatedAtDesc(recruitId)
                .stream()
                .map(ApplicantInfoDto::from)
                .collect(Collectors.toList());

        return RecruitOwnerResponseDto.from(recruit, applicantCount, applicants);
    }

    /**
     * 6. 동아리 모집글 수정
     */
    @Transactional
    public RecruitResponseDto updateRecruit(String usersId, Long recruitId, RecruitUpdateRequestDto requestDto) {
        UserEntity user = userRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        RecruitEntity recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RECRUIT_NOT_FOUND));

        // 게시자 본인인지 확인
        if (!recruit.getUser().getId().equals(user.getId())) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        recruit.update(
                requestDto.getTitle() != null ? requestDto.getTitle() : recruit.getTitle(),
                requestDto.getContent() != null ? requestDto.getContent() : recruit.getContent(),
                requestDto.getField() != null ? requestDto.getField() : recruit.getField(),
                requestDto.getGender() != null ? requestDto.getGender() : recruit.getGender(),
                requestDto.getPeople() != null ? requestDto.getPeople() : recruit.getPeople(),
                requestDto.getStartDate() != null ? requestDto.getStartDate() : recruit.getStartDate(),
                requestDto.getDueDate() != null ? requestDto.getDueDate() : recruit.getDueDate(),
                requestDto.getResultDate() != null ? requestDto.getResultDate() : recruit.getResultDate(),
                requestDto.getPosterImage() != null ? requestDto.getPosterImage() : recruit.getPosterImage()
        );

        return RecruitResponseDto.from(recruit);
    }

    /**
     * 7. 동아리 모집글 삭제
     */
    @Transactional
    public void deleteRecruit(String usersId, Long recruitId) {
        UserEntity user = userRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        RecruitEntity recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RECRUIT_NOT_FOUND));

        // 게시자 본인인지 확인
        if (!recruit.getUser().getId().equals(user.getId())) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        recruitRepository.delete(recruit);
    }
}

