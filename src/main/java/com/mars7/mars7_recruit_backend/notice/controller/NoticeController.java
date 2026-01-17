package com.mars7.mars7_recruit_backend.notice.controller;

import com.mars7.mars7_recruit_backend.common.dto.ApiResponse;
import com.mars7.mars7_recruit_backend.notice.dto.NoticeResponseDto;
import com.mars7.mars7_recruit_backend.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Notice", description = "알림 관련 API")
@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @Operation(summary = "내 알림 목록 조회", description = "로그인한 사용자의 알림 내역을 최신순으로 조회합니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<NoticeResponseDto>>> getAllNotices(Authentication authentication) {

        // JWT 토큰에서 로그인 아이디(usersId) 추출
        String usersId = authentication.getName();

        List<NoticeResponseDto> response = noticeService.getNotices(usersId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @Operation(summary = "알림 읽음 처리", description = "알림을 클릭했을 때 읽음 상태로 변경합니다.")
    @PatchMapping("/{noticeId}/read")
    public ResponseEntity<ApiResponse<Void>> markAsRead(
            Authentication authentication,
            @PathVariable Long noticeId) {

        String usersId = authentication.getName();
        noticeService.readNotice(noticeId, usersId);

        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}