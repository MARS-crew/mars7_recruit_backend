package com.mars7.mars7_recruit_backend.recruit.controller;

import com.mars7.mars7_recruit_backend.common.dto.ApiResponse;
import com.mars7.mars7_recruit_backend.common.enums.RecruitField;
import com.mars7.mars7_recruit_backend.recruit.dto.*;
import com.mars7.mars7_recruit_backend.recruit.service.RecruitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recruits")
@RequiredArgsConstructor
@Tag(name = "recruit-controller", description = "동아리 모집글 관련 API")
public class RecruitController {

    private final RecruitService recruitService;

    /**
     * 1. 동아리 검색 (키워드로 제목/내용 검색)
     */
    @GetMapping("/search")
    @Operation(summary = "동아리 검색", description = "키워드로 동아리 모집글을 검색합니다. 키워드가 없으면 전체 목록을 반환합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "검색 성공")
    public ApiResponse<List<RecruitListResponseDto>> searchRecruits(
            @Parameter(description = "검색 키워드 (선택사항)") @RequestParam(required = false) String keyword) {

        List<RecruitListResponseDto> result = recruitService.searchRecruits(keyword);
        return ApiResponse.ok(result);
    }

    /**
     * 2. 동아리 분야별 조회
     */
    @GetMapping
    @Operation(summary = "동아리 분야별 조회", description = "분야별로 동아리 모집글을 조회합니다. 분야를 지정하지 않거나 ALL을 선택하면 전체 목록을 반환합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    public ApiResponse<List<RecruitListResponseDto>> getRecruitsByField(
            @Parameter(description = "모집 분야 (ALL, MAJOR, HOBBY) - 선택사항") @RequestParam(required = false) RecruitField field) {

        List<RecruitListResponseDto> result = recruitService.getRecruitsByField(field);
        return ApiResponse.ok(result);
    }

    /**
     * 3. 동아리 모집글 작성
     */
    @PostMapping
    @Operation(summary = "동아리 모집글 작성", description = "새로운 동아리 모집글을 작성합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "작성 성공")
    public ApiResponse<RecruitResponseDto> createRecruit(
            Authentication authentication,
            @Valid @RequestBody RecruitCreateRequestDto requestDto) {

        String usersId = authentication.getName();
        RecruitResponseDto result = recruitService.createRecruit(usersId, requestDto);
        return ApiResponse.ok(result);
    }

    /**
     * 4. 동아리 모집글 상세 조회 (지원자용)
     */
    @GetMapping("/{recruitId}")
    @Operation(summary = "모집글 상세 조회 (지원자)", description = "동아리 모집글의 상세 정보를 조회합니다. 조회 시 조회수가 증가합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    public ApiResponse<RecruitResponseDto> getRecruitDetail(
            @Parameter(description = "모집글 ID") @PathVariable Long recruitId) {

        RecruitResponseDto result = recruitService.getRecruitDetail(recruitId);
        return ApiResponse.ok(result);
    }

    /**
     * 5. 동아리 모집글 상세 조회 (게시자용)
     */
    @GetMapping("/{recruitId}/owner")
    @Operation(summary = "모집글 상세 조회 (게시자)", description = "게시자 본인만 접근 가능하며, 지원자 수가 포함된 상세 정보를 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    public ApiResponse<RecruitOwnerResponseDto> getRecruitDetailForOwner(
            Authentication authentication,
            @Parameter(description = "모집글 ID") @PathVariable Long recruitId) {

        String usersId = authentication.getName();
        RecruitOwnerResponseDto result = recruitService.getRecruitDetailForOwner(usersId, recruitId);
        return ApiResponse.ok(result);
    }

    /**
     * 6. 동아리 모집글 수정
     */
    @PutMapping("/{recruitId}")
    @Operation(summary = "모집글 수정", description = "게시자 본인만 접근 가능하며, 모집글을 수정합니다. 수정하지 않을 필드는 null로 전송하거나 생략할 수 있습니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "수정 성공")
    public ApiResponse<RecruitResponseDto> updateRecruit(
            Authentication authentication,
            @Parameter(description = "모집글 ID") @PathVariable Long recruitId,
            @Valid @RequestBody RecruitUpdateRequestDto requestDto) {

        String usersId = authentication.getName();
        RecruitResponseDto result = recruitService.updateRecruit(usersId, recruitId, requestDto);
        return ApiResponse.ok(result);
    }

    /**
     * 7. 동아리 모집글 삭제
     */
    @DeleteMapping("/{recruitId}")
    @Operation(summary = "모집글 삭제", description = "게시자 본인만 접근 가능하며, 모집글을 삭제합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "삭제 성공")
    public ApiResponse<String> deleteRecruit(
            Authentication authentication,
            @Parameter(description = "모집글 ID") @PathVariable Long recruitId) {

        String usersId = authentication.getName();
        recruitService.deleteRecruit(usersId, recruitId);
        return ApiResponse.ok("모집글이 삭제되었습니다.");
    }
}

