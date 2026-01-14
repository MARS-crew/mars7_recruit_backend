package com.mars7.mars7_recruit_backend.resume.dto;

import com.mars7.mars7_recruit_backend.resume.entity.ResumeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
@Schema(description = "지원서 작성 응답 데이터")
public class ResumeResponseDto {

    @Schema(description = "생성된 지원서 ID", example = "1")
    private Long resumeId; // 이 필드가 반드시 있어야 합니다.

    @Schema(description = "생성된 지원서 ID", example = "1")
    private Long userId; // 이 필드가 반드시 있어야 합니다.

    @Schema(description = "모집글 ID", example = "1")
    private Long recruitId; // 이 필드가 반드시 있어야 합니다.

    @Schema(description = "지원서 제목", example = "백엔드 개발자 지원")
    private String title;

    @Schema(description = "자기소개", example = "안녕하세요...")
    private String selfIntroduce;


    @Schema(description = "작성 시간")
    private LocalDateTime createdAt;

    public static ResumeResponseDto from(ResumeEntity entity) {
        return ResumeResponseDto.builder()
                .resumeId(entity.getResumeId()) // 엔티티에서 ID를 가져옴
                .recruitId(entity.getRecruitId()) // 엔티티에서 ID를 가져옴
                .userId(entity.getUserId()) // 엔티티에서 ID를 가져옴
                .title(entity.getTitle())
                .selfIntroduce(entity.getSelfIntroduce())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}