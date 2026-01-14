package com.mars7.mars7_recruit_backend.mypage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PwChangeResponseDto {
    @Schema(description = "pk", example = "1")
    private Long id;

    @Schema(description = "로그인 아이디", example = "cye4526")
    private String usersId;

    @Schema(description = "메세지", example = "비밀번호가 변경되었습니다.")
    private String Message;
}
