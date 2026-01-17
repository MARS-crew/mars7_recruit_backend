package com.mars7.mars7_recruit_backend.recruit.repository;

import com.mars7.mars7_recruit_backend.recruit.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {

    // 특정 모집글��� 지원자 수 조회
    Integer countByRecruitRecruitId(Long recruitId);

    // 특정 모집글의 지원자 목록 조회 (최신순) - User 정보 함께 조회
    @Query("SELECT r FROM ResumeEntity r JOIN FETCH r.user WHERE r.recruit.recruitId = :recruitId ORDER BY r.createdAt DESC")
    List<ResumeEntity> findByRecruitRecruitIdOrderByCreatedAtDesc(@Param("recruitId") Long recruitId);

    // 특정 사용자가 특정 모집글에 지원했는지 확인
    boolean existsByUserIdAndRecruitRecruitId(Long userId, Long recruitId);

    // 지원서 조회 (User 정보 함께 조회)
    @Query("SELECT r FROM ResumeEntity r JOIN FETCH r.user JOIN FETCH r.recruit WHERE r.resumeId = :resumeId")
    Optional<ResumeEntity> findByIdWithUserAndRecruit(@Param("resumeId") Long resumeId);
}

