package com.mars7.mars7_recruit_backend.mypage.controller;

import com.mars7.mars7_recruit_backend.common.dto.ApiResponse;
import com.mars7.mars7_recruit_backend.mypage.dto.ChangeRequestDto;
import com.mars7.mars7_recruit_backend.mypage.dto.ChangeResponseDto;
import com.mars7.mars7_recruit_backend.mypage.service.MypageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    @Operation(summary = "사용자 정보 수정", description = "사용자 정보 수정, 정보 하나만 입력해도 수정 가능")
    @PatchMapping("api/v1/mypage/update")
    public ApiResponse<ChangeResponseDto> updateUserInfo(
            Authentication authentication,
            @RequestBody ChangeRequestDto dto
    ) {
        String usersId = authentication.getName();
        ChangeResponseDto response = mypageService.updateUserInfo(usersId, dto);
        return ApiResponse.ok(response);
    }
}
