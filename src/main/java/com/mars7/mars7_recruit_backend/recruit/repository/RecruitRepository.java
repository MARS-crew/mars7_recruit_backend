package com.mars7.mars7_recruit_backend.recruit.repository;

import com.mars7.mars7_recruit_backend.common.enums.RecruitField;
import com.mars7.mars7_recruit_backend.recruit.entity.RecruitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<RecruitEntity, Long> {

    // 제목 또는 내용으로 검색 (키워드 검색)
    @Query("SELECT r FROM RecruitEntity r WHERE r.title LIKE %:keyword% OR r.content LIKE %:keyword% ORDER BY r.createdAt DESC")
    Page<RecruitEntity> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    // 분야별 조회
    Page<RecruitEntity> findByFieldOrderByCreatedAtDesc(RecruitField field, Pageable pageable);

    // 전체 조회 (최신순)
    Page<RecruitEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

    // 특정 유저의 모집글 조회
    List<RecruitEntity> findByUserIdOrderByCreatedAtDesc(Long userId);
}

