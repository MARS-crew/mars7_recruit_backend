package com.mars7.mars7_recruit_backend.resume.repository;

import com.mars7.mars7_recruit_backend.resume.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("userResumeRepository")
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {

    // 특정 모집공고에 지원한 사람들 목록 조회
    List<ResumeEntity> findAllByRecruitId(Long recruitId);

    // 특정 유저(userId PK값)가 작성한 지원서 목록 조회
    List<ResumeEntity> findAllByUserId(Long userId);
}