package com.mars7.mars7_recruit_backend.mypage.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mars7.mars7_recruit_backend.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@JsonPropertyOrder({"id", "usersId", "name", "phoneNumber", "grade", "major", "profileImage", "apppushAgreed", "createdAt", "updatedAt"})
public class InfoCheckResponseDto {
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

    @Schema(description = "주소", example = "서울특별시 봉천동")
    private String address;

    @Schema(description = "프로필 이미지", example = "https://example.com/profile.jpg")
    private String profileImage;

    @Schema(description = "푸시 동의 여부", example = "true")
    private Boolean apppushAgreed;

    @Schema(description = "계정 생성 일시", example = "2026-01-10T23:45:00")
    private LocalDateTime createdAt;

    @Schema(description = "계정 수정 일시", example = "2026-01-10T23:45:00")
    private LocalDateTime updatedAt;
}
