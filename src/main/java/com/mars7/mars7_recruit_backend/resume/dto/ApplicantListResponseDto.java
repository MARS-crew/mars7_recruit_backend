package com.mars7.mars7_recruit_backend.resume.dto;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.resume.entity.ResumeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
@Schema(description = "지원자 상세 정보 응답")
public class ApplicantListResponseDto {

    @Schema(description = "지원서 ID", example = "1")
    private Long resumeId;



    @Schema(description = "지원 시간")
    private LocalDateTime createdAt;

    @Schema(description = "지원자 이름", example = "최예은")
    private String name;

    @Schema(description = "성별", example = "F")
    private String gender;
    @Schema(description = "학과", example = "3")
    private Integer grade;


    @Schema(description = "나이", example = "23") // 이상한 숫자 대신 23이 출력됩니다.
    private Integer age;

    @Schema(description = "학과", example = "컴퓨터공학과")
    private String major;

    @Schema(description = "프로필 이미지 URL", example = "https://example.com/profile.jpg")
    private String profileImage;

    public static ApplicantListResponseDto of(ResumeEntity resume, UserEntity user, Integer age) {
        return ApplicantListResponseDto.builder()
                .resumeId(resume.getResumeId())
                .createdAt(resume.getCreatedAt())
                .name(user.getName())
                .gender(user.getGender().toString())
                .grade(user.getGrade())
                .age(age)
                .major(user.getMajor())
                .profileImage(user.getProfileImage())
                .build();
    }
}