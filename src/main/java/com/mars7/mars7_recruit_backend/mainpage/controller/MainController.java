package com.mars7.mars7_recruit_backend.mainpage.controller;

import com.mars7.mars7_recruit_backend.common.dto.ApiResponse;
import com.mars7.mars7_recruit_backend.mainpage.dto.MainResponseDto;
import com.mars7.mars7_recruit_backend.mainpage.service.MainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mainpage")
@RequiredArgsConstructor
@Tag(name= "mainpage", description = "메인페이지 최신순/인기순 모집글 조회")
public class MainController {
    private final MainService mainService;

    @Operation(summary = "메인 페이지 데이터 조회", description = "최신순 3개, 인기순 3개 모집글 조회")
    @GetMapping
    public ApiResponse<MainResponseDto> getMainPage() {
        MainResponseDto response = mainService.getMainPageRecruits();

        return ApiResponse.ok(response);
    }
}
