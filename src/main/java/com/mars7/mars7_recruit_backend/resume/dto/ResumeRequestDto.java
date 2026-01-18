package com.mars7.mars7_recruit_backend.resume.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Schema(description = "지원서 작성 요청 데이터")
public class ResumeRequestDto {
    @Schema(description = "모집공고 ID", example = "1")
    private Long recruitId;

    @Schema(description = "지원서 제목", example = "백엔드 개발자 지원")
    private String title;

    @Schema(description = "자기소개", example = "안녕하세요...")
    private String selfIntroduce;
}