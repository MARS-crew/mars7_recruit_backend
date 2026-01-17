package com.mars7.mars7_recruit_backend.recruit.entity;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.common.entity.BaseCreatedTimeEntity;
import com.mars7.mars7_recruit_backend.common.enums.RecruitStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "resume")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
public class ResumeEntity extends BaseCreatedTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id", nullable = false)
    private Long resumeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id", nullable = false)
    private RecruitEntity recruit;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(name = "self_introduce", length = 500, nullable = false)
    private String selfIntroduce;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private RecruitStatus status = RecruitStatus.INPROGRESS;

    public void updateStatus(RecruitStatus status) {
        this.status = status;
    }
}

