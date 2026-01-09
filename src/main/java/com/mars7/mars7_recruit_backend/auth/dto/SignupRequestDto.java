package com.mars7.mars7_recruit_backend.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mars7.mars7_recruit_backend.common.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SignupRequestDto {
    @NotNull(message = "필수 입력값입니다.")
    @Size(min=3, max=15)
    private String usersId;

    @NotNull(message = "필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$",
            message = "비밀번호는 8~20자이며, 영문, 숫자, 특수문자를 포함해야 합니다.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull(message = "필수 입력값입니다.")
    private String name;

    @NotNull(message = "필수 입력값입니다.")
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "전화번호를 확인해 주세요.")
    private String phoneNumber;

    @NotNull(message = "필수 입력값입니다.")
    private Integer grade;

    @NotNull(message = "필수 입력값입니다.")
    private String major;

    @NotNull(message = "필수 입력값입니다.")
    private Gender gender;

    @NotNull(message = "필수 입력값입니다.")
    private LocalDate birth;

    private String profileImage;

    @NotNull(message = "필수 입력값입니다.")
    private String address;

    @NotNull(message = "필수 입력값입니다.")
    private Boolean serviceAgreed;

    @NotNull(message = "필수 입력값입니다.")
    private Boolean apppushAgreed;
}
