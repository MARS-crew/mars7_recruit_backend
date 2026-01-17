package com.mars7.mars7_recruit_backend.recruit.dto;

import com.mars7.mars7_recruit_backend.common.enums.RecruitStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "지원서 상태 업데이트 요청 DTO")
public class UpdateResumeStatusRequestDto {

    @NotNull(message = "상태는 필수입니다")
    @Schema(description = "지원 상태 (INPROGRESS, PASS, FAIL)", example = "PASS")
    private RecruitStatus status;
}

