package com.mars7.mars7_recruit_backend.mypage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InfoChangeRequestDto {

    @Schema(description = "이름", example = "최예은")
    private String name;

    @Schema(description = "전화번호", example = "010-9017-1111")
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "전화번호를 확인해 주세요.")
    private String phoneNumber;

    @Schema(description = "학년", example = "2")
    private Integer grade;

    @Schema(description = "전공", example = "컴퓨터정보공학과")
    private String major;

    @Schema(description = "프로필 이미지", example = "https://item.kakaocdn.net/do/37afd3f69a8b85bd2c9c490e7ab1ff617154249a3890514a43687a85e6b6cc82")
    private String profileImage;

    @Schema(description = "앱 푸시 동의 (Boolean)", example = "true")
    private Boolean apppushAgreed;
}
