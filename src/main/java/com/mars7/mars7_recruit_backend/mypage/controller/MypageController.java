package com.mars7.mars7_recruit_backend.mypage.controller;

import com.mars7.mars7_recruit_backend.common.dto.ApiResponse;
import com.mars7.mars7_recruit_backend.mypage.dto.*;
import com.mars7.mars7_recruit_backend.mypage.service.MypageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    @Operation(summary = "마이페이지 정보 조회", description = "사용자 정보 조회")
    @GetMapping
    public ApiResponse<InfoCheckResponseDto> getUserInfo(
            Authentication authentication
    ) {
        String usersId = authentication.getName();
        InfoCheckResponseDto response = mypageService.UserInfo(usersId);
        return ApiResponse.ok(response);
    }

    @Operation(summary = "사용자 정보 수정", description = "사용자 정보 수정, 정보 하나만 입력해도 수정 가능")
    @PatchMapping("/update")
    public ApiResponse<InfoChangeResponseDto> updateUserInfo(
            Authentication authentication,
            @RequestBody InfoChangeRequestDto dto
    ) {
        String usersId = authentication.getName();
        InfoChangeResponseDto response = mypageService.updateUserInfo(usersId, dto);
        return ApiResponse.ok(response);
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경")
    @PatchMapping("/password")
    public ApiResponse<PwChangeResponseDto> pwChange(
            Authentication authentication,
            @Valid @RequestBody PwChangeRequestDto dto
    ) {
        String usersId = authentication.getName();

        PwChangeResponseDto response = mypageService.pwChange(usersId, dto);

        return ApiResponse.ok(response);
    }

    @Operation(summary = "푸시 알림 변경", description = "푸시 알림 변경, true면 false > false면 true")
    @PatchMapping("/push")
    public ApiResponse<PushChangeResponeseDto> pushChange(
            Authentication authentication
    ){
        String usersId = authentication.getName();
        PushChangeResponeseDto response = mypageService.pushChange(usersId);
        return ApiResponse.ok(response);
    }

}
