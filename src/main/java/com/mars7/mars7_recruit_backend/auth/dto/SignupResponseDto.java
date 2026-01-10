package com.mars7.mars7_recruit_backend.auth.dto;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.common.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class SignupResponseDto {
    @Schema(description = "pk", example = "1")
    private Long id;

    @Schema(description = "로그인 아이디", example = "cye4526")
    private String usersId;

    @Schema(description = "이름", example = "최예은")
    private String name;

    @Schema(description = "전화번호", example = "010-9017-1111")
    private String phoneNumber;

    @Schema(description = "학년", example = "2")
    private Integer grade;

    @Schema(description = "전공", example = "컴퓨터정보공학과")
    private String major;

    @Schema(description = "성별", example = "F")
    private Gender gender;

    @Schema(description = "생년월일", example = "2005-07-11")
    private LocalDate birth;

    @Schema(description = "프로필 이미지", example = "https://example.com/profile.jpg")
    private String profileImage;

    @Schema(description = "주소", example = "서울특별시 봉천동")
    private String address;

    @Schema(description = "서비스 동의 여부", example = "true")
    private Boolean serviceAgreed;

    @Schema(description = "푸시 동의 여부", example = "true")
    private Boolean apppushAgreed;

    @Schema(description = "계정 생성 일시", example = "2026-01-10T23:45:00")
    private LocalDateTime createdAt;

    public static SignupResponseDto from(UserEntity entity) {
        return SignupResponseDto.builder()
                .id(entity.getId())
                .usersId(entity.getUsersId())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .grade(entity.getGrade())
                .major(entity.getMajor())
                .gender(entity.getGender())
                .birth(entity.getBirth())
                .profileImage(entity.getProfileImage())
                .address(entity.getAddress())
                .serviceAgreed(entity.getServiceAgreed())
                .apppushAgreed(entity.getApppushAgreed())
                .build();
    }
}
