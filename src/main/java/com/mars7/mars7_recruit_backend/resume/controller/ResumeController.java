package com.mars7.mars7_recruit_backend.resume.controller;

import com.mars7.mars7_recruit_backend.common.dto.ApiResponse;
import com.mars7.mars7_recruit_backend.resume.dto.ApplicantDetailResponseDto;
import com.mars7.mars7_recruit_backend.resume.dto.ApplicantListResponseDto;
import com.mars7.mars7_recruit_backend.resume.dto.ResumeRequestDto;
import com.mars7.mars7_recruit_backend.resume.dto.ResumeResponseDto;
import com.mars7.mars7_recruit_backend.resume.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication; //
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Resume", description = "지원서 관련 API")
@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
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
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @Operation(summary = "지원자 목록 조회", description = "공고 작성자 본인만 조회가 가능합니다.")
    @GetMapping("/{recruitId}/applicants")
    public ResponseEntity<ApiResponse<List<ApplicantListResponseDto>>> getApplicants(
            Authentication authentication,
            @PathVariable Long recruitId) {

        String usersId = authentication.getName(); // JWT 토큰의 usersId
        List<ApplicantListResponseDto> response = resumeService.getApplicantsByRecruitId(recruitId, usersId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @Operation(summary = "지원자 상세 조회", description = "공고 작성자 본인만 조회가 가능합니다.")
    @GetMapping("/{resumeId}/detail")
    public ResponseEntity<ApiResponse<ApplicantDetailResponseDto>> getApplicantDetail(
            Authentication authentication,
            @PathVariable Long resumeId) {

        String usersId = authentication.getName();
        ApplicantDetailResponseDto response = resumeService.getApplicantDetail(resumeId, usersId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @Operation(summary = "내 지원서 목록 조회")
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<ResumeResponseDto>>> getMyResumes(Authentication authentication) {
        String usersId = authentication.getName();
        List<ResumeResponseDto> response = resumeService.getMyResumes(usersId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
    @Operation(summary = "내 지원서 상세 조회", description = "로그인한 유저 본인이 작성한 지원서의 상세 내용을 조회합니다.")
    @GetMapping("/my/{resumeId}")
    public ResponseEntity<ApiResponse<ApplicantDetailResponseDto>> getMyResumeDetail(
            Authentication authentication,
            @PathVariable Long resumeId) {

        // JWT 토큰에서 로그인 아이디(usersId) 추출
        String usersId = authentication.getName();

        ApplicantDetailResponseDto response = resumeService.getMyResumeDetail(resumeId, usersId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}