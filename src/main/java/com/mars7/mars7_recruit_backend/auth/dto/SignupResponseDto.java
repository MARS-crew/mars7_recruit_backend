package com.mars7.mars7_recruit_backend.auth.dto;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.common.enums.Gender;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class SignupResponseDto {
    private Long id;
    private String usersId;
    private String name;
    private String phoneNumber;
    private Integer grade;
    private String major;
    private Gender gender;
    private LocalDate birth;
    private String profileImage;
    private String address;
    private Boolean serviceAgreed;
    private Boolean apppushAgreed;
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
