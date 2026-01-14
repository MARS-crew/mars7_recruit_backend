package com.mars7.mars7_recruit_backend.recruit.repository;

import com.mars7.mars7_recruit_backend.common.enums.RecruitField;
import com.mars7.mars7_recruit_backend.recruit.entity.RecruitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<RecruitEntity, Long> {

    // 제목 또는 내용으로 검색 (키워드 검색)
    @Query("SELECT r FROM RecruitEntity r WHERE r.title LIKE %:keyword% OR r.content LIKE %:keyword% ORDER BY r.createdAt DESC")
    List<RecruitEntity> searchByKeyword(@Param("keyword") String keyword);

    // 분야별 조회
    List<RecruitEntity> findByFieldOrderByCreatedAtDesc(RecruitField field);

    // 전체 조회 (최신순)
    List<RecruitEntity> findAllByOrderByCreatedAtDesc();

    // 특정 유저의 모집글 조회
    List<RecruitEntity> findByUserIdOrderByCreatedAtDesc(Long userId);


    //mainpage
    List<RecruitEntity> findTop3ByOrderByCreatedAtDesc();
    List<RecruitEntity> findTop3ByOrderByViewCountDesc();
}

