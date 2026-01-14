package com.mars7.mars7_recruit_backend.mainpage.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class MainResponseDto {
    private List<RecruitSummaryDto> latestRecruits;
    private List<RecruitSummaryDto> popularRecruits;

    @Getter
    @Builder
    public static class RecruitSummaryDto {
        private Long recruitId;
        private String title;
        private String content;
        private String posterImage;
        private Integer viewCount;
        private LocalDateTime startDate;
        private LocalDateTime dueDate;
        private Integer people;
        private LocalDateTime createdAt;
    }
}
