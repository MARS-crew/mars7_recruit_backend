package com.mars7.mars7_recruit_backend.resume.service;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.auth.repository.UserRepository; // 패키지 경로 확인 필요
import com.mars7.mars7_recruit_backend.resume.dto.ApplicantListResponseDto;
import com.mars7.mars7_recruit_backend.resume.dto.ResumeRequestDto;
import com.mars7.mars7_recruit_backend.resume.dto.ResumeResponseDto;
import com.mars7.mars7_recruit_backend.resume.entity.ResumeEntity;
import com.mars7.mars7_recruit_backend.resume.repository.ResumeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeService(
            @Qualifier("userResumeRepository") ResumeRepository resumeRepository,
            UserRepository userRepository) {
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }

    // 지원서 작성 (POST)
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

    @Transactional(readOnly = true)
    public List<ApplicantListResponseDto> getApplicantsByRecruitId(Long recruitId) {
        // findAll() 대신 특정 recruitId로 조회 (레포지토리에 해당 메서드 필요)
        List<ResumeEntity> resumes = resumeRepository.findAllByRecruitId(recruitId);

        return resumes.stream().map(resume -> {
            UserEntity user = userRepository.findById(resume.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. ID: " + resume.getUserId()));

            int age = LocalDateTime.now().getYear() - user.getBirth().getYear() + 1;

            return ApplicantListResponseDto.of(resume, user, age);
        }).toList();
    }
}