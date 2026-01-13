package com.mars7.mars7_recruit_backend.recruit.repository;

import com.mars7.mars7_recruit_backend.recruit.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {

    // 특정 모집글의 지원자 수 조회
    Integer countByRecruitRecruitId(Long recruitId);

    // 특정 사용자가 특정 모집글에 지원했는지 확인
    boolean existsByUserIdAndRecruitRecruitId(Long userId, Long recruitId);
}

