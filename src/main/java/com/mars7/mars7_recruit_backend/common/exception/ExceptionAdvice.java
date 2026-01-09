package com.mars7.mars7_recruit_backend.common.exception;

import com.mars7.mars7_recruit_backend.common.dto.ApiResponse;
import com.mars7.mars7_recruit_backend.common.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusiness(BusinessException e) {
        ErrorCode ec = e.getErrorCode();

        log.warn("[BusinessException] {}", ec.getCode());

        return ResponseEntity
                .status(ec.getStatus())
                .body(ApiResponse.error(ec));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {

        log.error("[Exception]", e);

        return ResponseEntity
                .status(ErrorCode.INTERNAL_ERROR.getStatus())
                .body(ApiResponse.error(ErrorCode.INTERNAL_ERROR));
    }
}
