package com.mars7.mars7_recruit_backend.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LogoutResponseDto {
    @Schema(description = "pk", example = "1")
    private Long id;

    @Schema(description = "로그인 아이디", example = "cye4526")
    private String usersId;

    @Schema(description = "이름", example = "최예은")
    private String name;

    @Schema(description = "메시지", example = "로그아웃되었습니다.")
    private String message;

}
