package com.mars7.mars7_recruit_backend.mypage.repository;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MypageRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsersId(String usersId);
    boolean existsByPhoneNumber(String phoneNumber);
}
