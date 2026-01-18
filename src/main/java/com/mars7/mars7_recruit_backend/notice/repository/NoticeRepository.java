package com.mars7.mars7_recruit_backend.notice.repository;

import com.mars7.mars7_recruit_backend.notice.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    // 서비스의 getNotices에서 호출하는 이름과 일치시켜야 함
    List<NoticeEntity> findByUserIdOrderByCreatedAtDesc(Long userId);

    // 특정 모집글과 관련된 모든 알림 삭제
    void deleteByRecruitId(Long recruitId);
}