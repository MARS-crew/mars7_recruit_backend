package com.mars7.mars7_recruit_backend.auth.controller;

import com.mars7.mars7_recruit_backend.auth.dto.SignupRequestDto;
import com.mars7.mars7_recruit_backend.auth.dto.SignupResponseDto;
import com.mars7.mars7_recruit_backend.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "auth-controller", description = "회원가입/로그인 엔드포인트")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입 api")
    @ApiResponse(responseCode = "200", description = "성공")
    public SignupResponseDto signUp(@Valid @RequestBody SignupRequestDto requestDto) {
        return authService.signUp(requestDto);
    }
}
