package com.mars7.mars7_recruit_backend.resume.service;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.auth.repository.UserRepository; // 패키지 경로 확인 필요
import com.mars7.mars7_recruit_backend.resume.dto.ResumeListResponseDto;
import com.mars7.mars7_recruit_backend.resume.dto.ResumeRequestDto;
import com.mars7.mars7_recruit_backend.resume.dto.ResumeResponseDto;
import com.mars7.mars7_recruit_backend.resume.entity.ResumeEntity;
import com.mars7.mars7_recruit_backend.resume.repository.ResumeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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

    // 지원서 목록 조회 (GET) - 유저 정보 포함
    @Transactional(readOnly = true)
    public List<ResumeListResponseDto> getAllResumes() {
        List<ResumeEntity> resumes = resumeRepository.findAll();

        return resumes.stream().map(resume -> {
            // 1. 해당 지원서의 유저 정보 조회
            UserEntity user = userRepository.findById(resume.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. ID: " + resume.getUserId()));

            // 2. 나이 계산 (한국식 세는 나이 또는 만나이)
            // 여기서는 생일(LocalDate) 기반으로 간단한 한국식 나이 계산 예시를 사용합니다.
            int age = LocalDateTime.now().getYear() - user.getBirth().getYear() + 1;

            // 3. 만들어두신 DTO의 'of' 메서드 활용
            return ResumeListResponseDto.of(resume, user, age);
        }).toList();
    }
}