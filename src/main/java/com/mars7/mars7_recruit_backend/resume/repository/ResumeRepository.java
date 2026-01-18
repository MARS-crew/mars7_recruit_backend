package com.mars7.mars7_recruit_backend.resume.repository;

import com.mars7.mars7_recruit_backend.resume.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("userResumeRepository")
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {

    // 특정 모집공고에 특정 유저가 이미 지원했는지 확인
    boolean existsByRecruitIdAndUserId(Long recruitId, Long userId);

    List<ResumeEntity> findAllByRecruitId(Long recruitId);
    List<ResumeEntity> findAllByUserId(Long userId);
}