package com.mars7.mars7_recruit_backend.recruit.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mars7.mars7_recruit_backend.recruit.entity.ResumeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonPropertyOrder({"resumeId", "userId", "userName", "phoneNumber", "grade", "major",
                     "title", "selfIntroduce", "createdAt"})
@Schema(description = "지원자 정보 DTO")
public class ApplicantInfoDto {

    @Schema(description = "지원서 ID", example = "1")
    private Long resumeId;

    @Schema(description = "지원자 ID", example = "1")
    private Long userId;

    @Schema(description = "지원자 이름", example = "김철수")
    private String userName;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;

    @Schema(description = "학년", example = "2")
    private Integer grade;

    @Schema(description = "전공", example = "컴퓨터공학과")
    private String major;

    @Schema(description = "지원서 제목", example = "열정 넘치는 개발자 지원합니다")
    private String title;

    @Schema(description = "자기소개", example = "안녕하세요. 저는...")
    private String selfIntroduce;

    @Schema(description = "지원일시", example = "2026-01-12T14:30:00")
    private LocalDateTime createdAt;

    public static ApplicantInfoDto from(ResumeEntity resume) {
        return ApplicantInfoDto.builder()
                .resumeId(resume.getResumeId())
                .userId(resume.getUser().getId())
                .userName(resume.getUser().getName())
                .phoneNumber(resume.getUser().getPhoneNumber())
                .grade(resume.getUser().getGrade())
                .major(resume.getUser().getMajor())
                .title(resume.getTitle())
                .selfIntroduce(resume.getSelfIntroduce())
                .createdAt(resume.getCreatedAt())
                .build();
    }
}

