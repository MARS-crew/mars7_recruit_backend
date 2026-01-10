package com.mars7.mars7_recruit_backend.auth.controller;

import com.mars7.mars7_recruit_backend.auth.dto.SignupRequestDto;
import com.mars7.mars7_recruit_backend.auth.dto.SignupResponseDto;
import com.mars7.mars7_recruit_backend.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.mars7.mars7_recruit_backend.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "auth-controller", description = "회원가입/로그인 엔드포인트")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입 api")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    public SignupResponseDto signUp(@Valid @RequestBody SignupRequestDto requestDto) {
        return authService.signUp(requestDto);
    }

    @PostMapping("/check-id")
    @Operation(summary = "아이디 중복 확인", description = "아이디 중복 확인 api")
    public ApiResponse<String> checkId(@RequestParam String usersId) {
        String message = authService.checkUsersIdDuplicate(usersId);
        return ApiResponse.ok(message);
    }
}
