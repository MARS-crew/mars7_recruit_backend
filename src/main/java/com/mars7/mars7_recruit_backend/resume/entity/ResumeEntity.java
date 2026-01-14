package com.mars7.mars7_recruit_backend.resume.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity(name = "UserResumeEntity")
@Table(name = "resume")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ResumeEntity {

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

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}