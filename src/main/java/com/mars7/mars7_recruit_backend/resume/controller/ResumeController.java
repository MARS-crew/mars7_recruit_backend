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

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
@Tag(name = "Resume", description = "지원서 관련 API")
public class ResumeController {

    private final ResumeService resumeService;
    //지원서 작성
    @Operation(summary = "지원서 작성", description = "새로운 지원서를 제출하고 결과를 반환합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @PostMapping
    public ResponseEntity<ApiResponse<ResumeResponseDto>> submitResume(@RequestBody ResumeRequestDto requestDto) {
        ResumeResponseDto response = resumeService.submitResume(requestDto);
        // ApiResponse.ok()를 통해 success: true, data: response, error: null 상태로 반환
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
    //지원서 리스트 조회
    @Operation(summary = "지원서 전체 리스트 조회", description = "제출된 모든 지원서의 목록을 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ResumeResponseDto>>> getAllResumes() {
        // 서비스에서 DTO 리스트를 받아옴
        List<ResumeResponseDto> response = resumeService.getAllResumes();

        // 공통 응답 규격에 맞춰 반환 (success: true, data: [리스트], error: null)
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}