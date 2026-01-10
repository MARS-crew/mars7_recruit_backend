package com.mars7.mars7_recruit_backend.auth.repository;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsersId(String usersId);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<UserEntity> findByUsersId(String usersId);
}