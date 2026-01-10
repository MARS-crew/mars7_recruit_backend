package com.mars7.mars7_recruit_backend.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private SignupResponseDto userDetails;
}
