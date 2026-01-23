package com.mars7.mars7_recruit_backend.mypage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PushChangeResponeseDto {
    @Schema(description = "pk", example = "1")
    private Long id;

    @Schema(description = "로그인 아이디", example = "cye4526")
    private String usersId;

    @Schema(description = "이름", example = "최예은")
    private String name;

    @Schema(description = "푸시 동의 여부", example = "true")
    private Boolean apppushAgreed;

    @Schema(description = "계정 수정 일시", example = "2026-01-10T23:45:00")
    private LocalDateTime updatedAt;

}
