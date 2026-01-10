package com.mars7.mars7_recruit_backend.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mars7.mars7_recruit_backend.common.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SignupRequestDto {
    @Schema(description = "로그인 아이디", example = "cye4526")
    @NotNull(message = "필수 입력값입니다.")
    @Size(min=3, max=15)
    private String usersId;

    @Schema(description = "비밀번호", example = "cye1111*")
    @NotNull(message = "필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$",
            message = "비밀번호는 8~20자이며, 영문, 숫자, 특수문자를 포함해야 합니다.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Schema(description = "비밀번호 확인", example = "cye1111*")
    @NotNull(message = "필수 입력값입니다.")
    private String passwordConfirm;

    @Schema(description = "이름", example = "최예은")
    @NotNull(message = "필수 입력값입니다.")
    private String name;

    @Schema(description = "전화번호", example = "010-9017-1111")
    @NotNull(message = "필수 입력값입니다.")
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "전화번호를 확인해 주세요.")
    private String phoneNumber;

    @Schema(description = "학년", example = "2")
    @NotNull(message = "필수 입력값입니다.")
    private Integer grade;

    @Schema(description = "전공", example = "컴퓨터정보공학과")
    @NotNull(message = "필수 입력값입니다.")
    private String major;

    @Schema(description = "성별 (M or F)", example = "F")
    @NotNull(message = "필수 입력값입니다.")
    private Gender gender;

    @Schema(description = "생년월일", example = "2005-07-11")
    @NotNull(message = "필수 입력값입니다.")
    private LocalDate birth;

    @Schema(description = "프로필 이미지", example = "https://item.kakaocdn.net/do/37afd3f69a8b85bd2c9c490e7ab1ff617154249a3890514a43687a85e6b6cc82")
    private String profileImage;

    @Schema(description = "주소", example = "서울특별시 봉천동")
    @NotNull(message = "필수 입력값입니다.")
    private String address;

    @Schema(description = "서비스 이용약관 동의 (Boolean)", example = "true")
    @NotNull(message = "필수 입력값입니다.")
    private Boolean serviceAgreed;

    @Schema(description = "앱 푸시 동의 (Boolean)", example = "true")
    @NotNull(message = "필수 입력값입니다.")
    private Boolean apppushAgreed;
}
