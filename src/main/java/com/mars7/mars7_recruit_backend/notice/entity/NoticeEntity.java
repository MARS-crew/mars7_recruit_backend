package com.mars7.mars7_recruit_backend.notice.entity;

import com.mars7.mars7_recruit_backend.common.entity.BaseCreatedTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "UserNotice")
@Table(name = "notice")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class NoticeEntity extends BaseCreatedTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    @Column(name = "user_id", nullable = false)
    private Long userId; // DB PK와 매핑되는 필드

    @Column(name = "recruit_id", nullable = false)
    private Long recruitId;

    @Column(length = 50, nullable = false)
    private String noticeContent;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    // 에러 해결: readNotice에서 사용하는 상태 변경 메서드
    public void updateIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
}