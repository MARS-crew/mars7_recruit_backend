package com.mars7.mars7_recruit_backend.common.exception;

import com.mars7.mars7_recruit_backend.common.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    // 비즈니스 예외
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> businessException(BusinessException e) {
        log.warn("[BusinessException] {}", e.getMessage());
        return ApiResponse.error(e.getCode(), e.getMessage());
    }

    // 예상 못한 예외
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> exception(Exception e) {
        log.error("[Exception]", e);
        return ApiResponse.error(
                "INTERNAL_ERROR",
                "서버 내부 오류가 발생했습니다."
        );
    }
}
