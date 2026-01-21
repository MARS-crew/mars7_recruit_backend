package com.mars7.mars7_recruit_backend.recruit.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mars7.mars7_recruit_backend.common.enums.RecruitField;
import com.mars7.mars7_recruit_backend.common.enums.RecruitGender;
import com.mars7.mars7_recruit_backend.recruit.entity.RecruitEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonPropertyOrder({"recruitId", "title", "content", "field", "gender", "people", "dueDate", "posterImage", "viewCount", "createdAt"})
@Schema(description = "모집글 목록 조회용 간략 응답 DTO")
public class RecruitListResponseDto {

    @Schema(description = "모집글 ID", example = "1")
    private Long recruitId;

    @Schema(description = "모집글 제목", example = "프로그래밍 동아리 신입 부원 모집")
    private String title;

    @Schema(description = "모집글 내용", example = "열정 있는 분들의 많은 지원 바랍니다!")
    private String content;

    @Schema(description = "모집 분야", example = "MAJOR")
    private RecruitField field;

    @Schema(description = "모집 성별", example = "ANY")
    private RecruitGender gender;

    @Schema(description = "모집 인원", example = "10")
    private Integer people;

    @Schema(description = "모집 마감일", example = "2026-01-31T23:59:59")
    private LocalDateTime dueDate;

    @Schema(description = "포스터 이미지 URL", example = "https://example.com/poster.jpg")
    private String posterImage;

    @Schema(description = "조회수", example = "150")
    private Integer viewCount;

    @Schema(description = "생성일시", example = "2026-01-10T10:30:00")
    private LocalDateTime createdAt;

    public static RecruitListResponseDto from(RecruitEntity entity) {
        return RecruitListResponseDto.builder()
                .recruitId(entity.getRecruitId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .field(entity.getField())
                .gender(entity.getGender())
                .people(entity.getPeople())
                .dueDate(entity.getDueDate())
                .posterImage(entity.getPosterImage())
                .viewCount(entity.getViewCount())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
