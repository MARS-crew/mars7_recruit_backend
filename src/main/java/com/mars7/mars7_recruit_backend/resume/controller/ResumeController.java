package com.mars7.mars7_recruit_backend.resume.controller;

import com.mars7.mars7_recruit_backend.common.dto.ApiResponse;
import com.mars7.mars7_recruit_backend.resume.dto.ApplicantDetailResponseDto;
import com.mars7.mars7_recruit_backend.resume.dto.ApplicantListResponseDto;
import com.mars7.mars7_recruit_backend.resume.dto.ResumeRequestDto;
import com.mars7.mars7_recruit_backend.resume.dto.ResumeResponseDto;
import com.mars7.mars7_recruit_backend.resume.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    // 지원서 작성
    @Operation(summary = "지원서 작성", description = "새로운 지원서를 제출하고 결과를 반환합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @PostMapping
    public ResponseEntity<ApiResponse<ResumeResponseDto>> submitResume(@RequestBody ResumeRequestDto requestDto) {
        ResumeResponseDto response = resumeService.submitResume(requestDto);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 지원자 목록 조회 (특정 공고 기준)
    @Operation(summary = "지원자 목록 조회", description = "특정 모집글의 지원자 목록을 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/{recruitId}/applicants")
    public ResponseEntity<ApiResponse<List<ApplicantListResponseDto>>> getApplicants(
            @Parameter(description = "모집글 ID") @PathVariable Long recruitId) {

        List<ApplicantListResponseDto> response = resumeService.getApplicantsByRecruitId(recruitId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 지원자 상세 조회 (특정 지원서 기준)
    @Operation(summary = "지원자 상세 조회", description = "특정 지원서의 상세 정보(자기소개, 연락처 등)를 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/{resumeId}/detail")
    public ResponseEntity<ApiResponse<ApplicantDetailResponseDto>> getApplicantDetail(
            @Parameter(description = "지원서 ID") @PathVariable Long resumeId) {

        // 서비스에서 상세 정보를 가져옴
        ApplicantDetailResponseDto response = resumeService.getApplicantDetail(resumeId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}