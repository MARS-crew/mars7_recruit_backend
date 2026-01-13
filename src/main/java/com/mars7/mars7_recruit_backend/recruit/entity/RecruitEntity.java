package com.mars7.mars7_recruit_backend.recruit.entity;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.common.enums.RecruitField;
import com.mars7.mars7_recruit_backend.common.enums.RecruitGender;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "recruit")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
public class RecruitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id", nullable = false)
    private Long recruitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecruitField field;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecruitGender gender;

    @Column(nullable = false)
    private Integer people;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "result_date", nullable = false)
    private LocalDateTime resultDate;

    @Column(name = "poster_image", columnDefinition = "TEXT")
    private String posterImage;

    @Column(name = "view_count", nullable = false)
    @Builder.Default
    private Integer viewCount = 0;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public void increaseViewCount() {
        this.viewCount++;
    }

    public void update(String title, String content, RecruitField field, RecruitGender gender,
                       Integer people, LocalDateTime startDate, LocalDateTime dueDate,
                       LocalDateTime resultDate, String posterImage) {
        this.title = title;
        this.content = content;
        this.field = field;
        this.gender = gender;
        this.people = people;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.resultDate = resultDate;
        this.posterImage = posterImage;
    }
}

