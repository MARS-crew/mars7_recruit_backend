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
public class ApplicantDetailResponseDto {

    @Schema(description = "지원서 ID", example = "1")
    private Long resumeId;

    @Schema(description = "타이틀", example = "백엔드 개발자 지원")
    private String title;

    @Schema(description = "지원자 이름", example = "최예은")
    private String name;

    @Schema(description = "성별", example = "F")
    private String gender;

    @Schema(description = "학년", example = "3")
    private Integer grade;

    @Schema(description = "나이", example = "23")
    private Integer age;

    @Schema(description = "학과", example = "컴퓨터공학과")
    private String major;

    @Schema(description = "프로필 이미지 URL", example = "https://example.com/profile.jpg")
    private String profileImage;

    @Schema(description = "주소", example = "우암센스뷰")
    private String address;

    @Schema(description = "연락처", example = "010-9017-1111")
    private String phoneNumber;

    @Schema(description = "자기소개", example = "안녕하세요...")
    private String selfIntroduce;

    public static ApplicantDetailResponseDto of(ResumeEntity resume, UserEntity user, Integer age) {
        return ApplicantDetailResponseDto.builder()
                .resumeId(resume.getResumeId())
                .title(resume.getTitle())
                .name(user.getName())
                .gender(user.getGender().toString())
                .grade(user.getGrade())
                .age(age)
                .major(user.getMajor())
                .profileImage(user.getProfileImage())
                .phoneNumber(user.getPhoneNumber())
                .selfIntroduce(resume.getSelfIntroduce())
                .address(user.getAddress())
                .build();
    }
}