package com.mars7.mars7_recruit_backend.resume.controller;

import com.mars7.mars7_recruit_backend.common.dto.ApiResponse;
import com.mars7.mars7_recruit_backend.resume.dto.ResumeRequestDto;
import com.mars7.mars7_recruit_backend.resume.dto.ResumeResponseDto;
import com.mars7.mars7_recruit_backend.resume.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
@Tag(name = "Resume", description = "지원서 관련 API")
public class ResumeController {

    private final ResumeService resumeService;

    @Operation(summary = "지원서 작성", description = "새로운 지원서를 제출하고 결과를 반환합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @PostMapping
    public ResponseEntity<ApiResponse<ResumeResponseDto>> submitResume(@RequestBody ResumeRequestDto requestDto) {
        ResumeResponseDto response = resumeService.submitResume(requestDto);
        // ApiResponse.ok()를 통해 success: true, data: response, error: null 상태로 반환
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}