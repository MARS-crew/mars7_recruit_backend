package com.mars7.mars7_recruit_backend.notice.dto;

import com.mars7.mars7_recruit_backend.notice.entity.NoticeEntity;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class NoticeResponseDto {
    private Long noticeId;
    private Long recruitId;
    private String noticeContent;
    private Boolean isRead;
    private LocalDateTime createdAt;

    public NoticeResponseDto(NoticeEntity entity) {
        this.noticeId = entity.getNoticeId();
        this.recruitId = entity.getRecruitId();
        this.noticeContent = entity.getNoticeContent();
        this.isRead = entity.getIsRead();
        this.createdAt = entity.getCreatedAt();
    }
}