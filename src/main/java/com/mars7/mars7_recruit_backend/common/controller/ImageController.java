package com.mars7.mars7_recruit_backend.common.controller;

import com.mars7.mars7_recruit_backend.common.dto.ApiResponse;
import com.mars7.mars7_recruit_backend.common.dto.ImageUploadResponseDto;
import com.mars7.mars7_recruit_backend.common.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
@Tag(name = "image-controller", description = "이미지 업로드 관련 API")
public class ImageController {

    private final FileUploadService fileUploadService;

    /**
     * 1. 마이페이지 프로필 사진 업로드
     */
    @PostMapping(value = "/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "프로필 사진 업로드 (마이페이지)", description = "마이페이지에서 프로필 사진을 업로드합니다. 허용 형식: jpg, png, webp / 최대 크기: 10MB")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "업로드 성공")
    public ApiResponse<ImageUploadResponseDto> uploadProfileImage(
            Authentication authentication,
            @Parameter(description = "업로드할 이미지 파일", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @RequestPart("file") MultipartFile file) {

        String imageUrl = fileUploadService.uploadFile(file, "profile");
        return ApiResponse.ok(new ImageUploadResponseDto(imageUrl));
    }

    /**
     * 2. 회원가입 시 초기 프로필 사진 업로드
     */
    @PostMapping(value = "/signup/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "프로필 사진 업로드 (회원가입)", description = "회원가입 시 초기 프로필 사진을 업로드합니다. 허용 형식: jpg, png, webp / 최대 크기: 10MB")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "업로드 성공")
    public ApiResponse<ImageUploadResponseDto> uploadSignupProfileImage(
            @Parameter(description = "업로드할 이미지 파일", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @RequestPart("file") MultipartFile file) {

        String imageUrl = fileUploadService.uploadFile(file, "profile");
        return ApiResponse.ok(new ImageUploadResponseDto(imageUrl));
    }

    /**
     * 3. 동아리 모집글 포스터 이미지 업로드
     */
    @PostMapping(value = "/recruit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "모집글 포스터 이미지 업로드", description = "동아리 모집글 작성 시 포스터 이미지를 업로드합니다. 허용 형식: jpg, png, webp / 최대 크기: 10MB")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "업로드 성공")
    public ApiResponse<ImageUploadResponseDto> uploadRecruitImage(
            Authentication authentication,
            @Parameter(description = "업로드할 이미지 파일", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @RequestPart("file") MultipartFile file) {

        String imageUrl = fileUploadService.uploadFile(file, "recruit");
        return ApiResponse.ok(new ImageUploadResponseDto(imageUrl));
    }
}
