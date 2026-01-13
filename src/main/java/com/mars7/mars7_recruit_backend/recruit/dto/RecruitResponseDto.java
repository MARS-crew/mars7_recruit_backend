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
@JsonPropertyOrder({"recruitId", "userId", "userName", "userPhoneNumber", "title", "content", "field", "gender",
        "people", "startDate", "dueDate", "resultDate", "posterImage", "viewCount", "createdAt", "updatedAt"})
@Schema(description = "모집글 응답 DTO (지원자용)")
public class RecruitResponseDto {

    @Schema(description = "모집글 ID", example = "1")
    private Long recruitId;

    @Schema(description = "작성자 ID", example = "1")
    private Long userId;

    @Schema(description = "작성자 이름", example = "홍길동")
    private String userName;

    @Schema(description = "작성자 전화번호", example = "010-1234-5678")
    private String userPhoneNumber;

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

    @Schema(description = "모집 시작일", example = "2026-01-15T00:00:00")
    private LocalDateTime startDate;

    @Schema(description = "모집 마감일", example = "2026-01-31T23:59:59")
    private LocalDateTime dueDate;

    @Schema(description = "결과 발표일", example = "2026-02-05T12:00:00")
    private LocalDateTime resultDate;

    @Schema(description = "포스터 이미지 URL", example = "https://example.com/poster.jpg")
    private String posterImage;

    @Schema(description = "조회수", example = "150")
    private Integer viewCount;

    @Schema(description = "생성일시", example = "2026-01-10T10:30:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일시", example = "2026-01-10T10:30:00")
    private LocalDateTime updatedAt;

    public static RecruitResponseDto from(RecruitEntity entity) {
        return RecruitResponseDto.builder()
                .recruitId(entity.getRecruitId())
                .userId(entity.getUser().getId())
                .userName(entity.getUser().getName())
                .userPhoneNumber(entity.getUser().getPhoneNumber())
                .title(entity.getTitle())
                .content(entity.getContent())
                .field(entity.getField())
                .gender(entity.getGender())
                .people(entity.getPeople())
                .startDate(entity.getStartDate())
                .dueDate(entity.getDueDate())
                .resultDate(entity.getResultDate())
                .posterImage(entity.getPosterImage())
                .viewCount(entity.getViewCount())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}

