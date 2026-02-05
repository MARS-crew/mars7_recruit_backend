package com.mars7.mars7_recruit_backend.resume.entity;

import com.mars7.mars7_recruit_backend.common.entity.BaseCreatedTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "UserResumeEntity")
@Table(name = "resume")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ResumeEntity extends BaseCreatedTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long resumeId;

    @Column(name = "recruit_id", nullable = false)
    private Long recruitId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(name = "self_introduce", length = 500, nullable = false)
    private String selfIntroduce;

    @Column(name = "is_read", nullable = false)
    @Builder.Default
    private Boolean isRead = false;

    /**
     * 열람 상태를 true로 변경
     */
    public void markAsRead() {
        this.isRead = true;
    }
}