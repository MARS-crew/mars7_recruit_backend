package com.mars7.mars7_recruit_backend.resume.dto;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.resume.entity.ResumeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@Schema(description = "지원서 목록 조회 응답 데이터")
public class ResumeListResponseDto {

    @Schema(description = "지원서 ID", example = "1")
    private Long resumeId;

    @Schema(description = "작성 시간")
    private LocalDateTime createdAt;

    @Schema(description = "지원자 이름", example = "최예은")
    private String name;

    @Schema(description = "성별", example = "F")
    private String gender;
    @Schema(description = "학년", example = "3학년")
    private Integer grade;

    @Schema(description = "나이(생년월일 기반)", example = "22")
    private Integer age;

    @Schema(description = "학과", example = "컴퓨터공학과")
    private String major;

    @Schema(description = "프로필 이미지 URL")
    private String profileImage;

    /**
     * ResumeEntity와 연관된 User 정보를 받아 DTO로 변환합니다.
     * User 객체의 타입은 프로젝트 구성에 따라 UserEntity 등을 대입하세요.
     */
    public static ResumeListResponseDto of(ResumeEntity resume, UserEntity user, Integer age) {
        return ResumeListResponseDto.builder()
                .resumeId(resume.getResumeId())

                .createdAt(resume.getCreatedAt())
                .name(user.getName())
                .grade(user.getGrade())
                .gender(user.getGender().toString())
                .age(age)
                .major(user.getMajor())
                .profileImage(user.getProfileImage())
                .build();
    }
}