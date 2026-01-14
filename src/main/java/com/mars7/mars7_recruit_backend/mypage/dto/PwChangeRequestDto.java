package com.mars7.mars7_recruit_backend.mypage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PwChangeRequestDto {
    @Schema(description = "변경할 비밀번호", example = "cye1111*")
    @NotNull(message = "필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$",
            message = "비밀번호는 8~20자이며, 영문, 숫자, 특수문자를 포함해야 합니다.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Schema(description = "비밀번호 확인", example = "cye1111*")
    @NotNull(message = "필수 입력값입니다.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordConfirm;
}
