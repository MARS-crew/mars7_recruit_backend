package com.mars7.mars7_recruit_backend.resume.repository;

import com.mars7.mars7_recruit_backend.resume.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userResumeRepository")
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {

    // 특정 모집공고 ID(recruitId)에 해당하는 지원서 목록을 조회하는 메서드 추가
    List<ResumeEntity> findAllByRecruitId(Long recruitId);
}