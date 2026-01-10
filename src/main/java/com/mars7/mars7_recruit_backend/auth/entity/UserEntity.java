package com.mars7.mars7_recruit_backend.auth.entity;

import com.mars7.mars7_recruit_backend.common.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name= "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 15, nullable = false, unique = true, name="users_id")
    private String usersId;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 15, nullable = false, unique = true, name="phone_number")
    private String phoneNumber;

    @Column(nullable = false)
    private Integer grade;

    @Column(length = 30, nullable = false)
    private String major;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false, name="profile_image")
    private String profileImage;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, name= "service_agreed")
    private Boolean serviceAgreed = false;

    @Column(nullable = false, name= "apppush_agreed")
    private Boolean apppushAgreed = false;

    @CreatedDate
    @Column(updatable = false, name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(updatable = true, name="updated_at")
    private LocalDateTime updatedAt;
}