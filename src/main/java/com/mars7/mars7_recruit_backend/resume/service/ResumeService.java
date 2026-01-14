package com.mars7.mars7_recruit_backend.resume.service;

import com.mars7.mars7_recruit_backend.resume.dto.ResumeRequestDto;
import com.mars7.mars7_recruit_backend.resume.dto.ResumeResponseDto;
import com.mars7.mars7_recruit_backend.resume.entity.ResumeEntity;
import com.mars7.mars7_recruit_backend.resume.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeService(@Qualifier("userResumeRepository") ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

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
    public List<ResumeResponseDto> getAllResumes() {
        // 1. 모든 엔티티 조회
        List<ResumeEntity> resumes = resumeRepository.findAll();

        // 2. 엔티티 리스트를 DTO 리스트로 변환하여 반환
        return resumes.stream()
                .map(ResumeResponseDto::from)
                .collect(Collectors.toList());
    }
}