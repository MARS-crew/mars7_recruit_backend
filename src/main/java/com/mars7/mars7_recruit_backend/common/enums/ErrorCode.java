package com.mars7.mars7_recruit_backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_INPUT(HttpStatus.BAD_REQUEST, "INVALID_INPUT", "잘못된 요청입니다"),
    DUPLICATE_USER(HttpStatus.CONFLICT, "DUPLICATE_USER", "이미 존재하는 아이디입니다"), // 추가
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "인증이 필요합니다"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN", "권한이 없습니다"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "서버 오류"),
    DUPLICATE_PHONE(HttpStatus.CONFLICT, "DUPLICATE_PHONE", "이미 등록된 전화번호입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}