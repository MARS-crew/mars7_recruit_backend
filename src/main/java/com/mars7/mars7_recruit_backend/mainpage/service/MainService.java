package com.mars7.mars7_recruit_backend.mainpage.service;

import com.mars7.mars7_recruit_backend.mainpage.dto.MainResponseDto;
import com.mars7.mars7_recruit_backend.recruit.entity.RecruitEntity;
import com.mars7.mars7_recruit_backend.recruit.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {
    private final RecruitRepository recruitRepository;

    @Transactional(readOnly = true)
    public MainResponseDto getMainPageRecruits() {
        List<RecruitEntity> latestEntities = recruitRepository.findTop3ByOrderByCreatedAtDesc();
        List<RecruitEntity> popularEntities = recruitRepository.findTop3ByOrderByViewCountDesc();

        List<MainResponseDto.RecruitSummaryDto> latestDtos = latestEntities.stream()
                .map(this::convertToSummaryDto)
                .toList();

        List<MainResponseDto.RecruitSummaryDto> popularDtos = popularEntities.stream()
                .map(this::convertToSummaryDto)
                .toList();

        return MainResponseDto.builder()
                .latestRecruits(latestDtos)
                .popularRecruits(popularDtos)
                .build();
    }

    private MainResponseDto.RecruitSummaryDto convertToSummaryDto(RecruitEntity entity) {
        return MainResponseDto.RecruitSummaryDto.builder()
                .recruitId(entity.getRecruitId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .posterImage(entity.getPosterImage())
                .viewCount(entity.getViewCount())
                .startDate(entity.getStartDate())
                .dueDate(entity.getDueDate())
                .people(entity.getPeople())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
