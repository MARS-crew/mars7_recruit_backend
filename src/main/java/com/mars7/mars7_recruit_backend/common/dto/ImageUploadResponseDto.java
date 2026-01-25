package com.mars7.mars7_recruit_backend.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "이미지 업로드 응답")
public class ImageUploadResponseDto {

    @Schema(description = "업로드된 이미지 URL", example = "http://125.133.62.199:9000/images/profile/abc123.jpg")
    private String imageUrl;
}
