package com.mars7.mars7_recruit_backend.resume.repository;

import com.mars7.mars7_recruit_backend.resume.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userResumeRepository") // 서비스의 @Qualifier와 이름을 맞춤
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {
}