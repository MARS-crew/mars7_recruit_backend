package com.mars7.mars7_recruit_backend.recruit.dto;

import com.mars7.mars7_recruit_backend.common.enums.RecruitField;
import com.mars7.mars7_recruit_backend.common.enums.RecruitGender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Schema(description = "모집글 작성 요청 DTO")
public class RecruitCreateRequestDto {

    @Schema(description = "모집글 제목", example = "프로그래밍 동아리 신입 부원 모집")
    @NotNull(message = "제목은 필수 입력값입니다.")
    @Size(min = 1, max = 50, message = "제목은 1~50자 이내로 입력해주세요.")
    private String title;

    @Schema(description = "모집글 내용", example = "열정 있는 분들의 많은 지원 바랍니다!")
    @NotNull(message = "내용은 필수 입력값입니다.")
    @Size(min = 1, max = 500, message = "내용은 1~500자 이내로 입력해주세요.")
    private String content;

    @Schema(description = "모집 분야 (ALL, MAJOR, HOBBY)", example = "MAJOR")
    @NotNull(message = "모집 분야는 필수 입력값입니다.")
    private RecruitField field;

    @Schema(description = "모집 성별 (ANY, M, F)", example = "ANY")
    @NotNull(message = "모집 성별은 필수 입력값입니다.")
    private RecruitGender gender;

    @Schema(description = "모집 인원", example = "10")
    @NotNull(message = "모집 인원은 필수 입력값입니다.")
    private Integer people;

    @Schema(description = "모집 시작일", example = "2026-01-15T00:00:00")
    @NotNull(message = "모집 시작일은 필수 입력값입니다.")
    private LocalDateTime startDate;

    @Schema(description = "모집 마감일", example = "2026-01-31T23:59:59")
    @NotNull(message = "모집 마감일은 필수 입력값입니다.")
    private LocalDateTime dueDate;

    @Schema(description = "결과 발표일", example = "2026-02-05T12:00:00")
    @NotNull(message = "결과 발표일은 필수 입력값입니다.")
    private LocalDateTime resultDate;

    @Schema(description = "포스터 이미지 URL", example = "https://example.com/poster.jpg")
    private String posterImage;
}

