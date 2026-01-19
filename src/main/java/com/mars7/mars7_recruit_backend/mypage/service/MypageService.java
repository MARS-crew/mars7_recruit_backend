package com.mars7.mars7_recruit_backend.mypage.service;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.common.enums.ErrorCode;
import com.mars7.mars7_recruit_backend.common.exception.BusinessException;
import com.mars7.mars7_recruit_backend.mypage.dto.*;
import com.mars7.mars7_recruit_backend.mypage.repository.MypageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MypageRepository mypageRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public InfoCheckResponseDto UserInfo(String usersId) {
        UserEntity user = mypageRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return InfoCheckResponseDto.builder()
                .id(user.getId())
                .usersId(user.getUsersId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .grade(user.getGrade())
                .major(user.getMajor())
                .profileImage(user.getProfileImage())
                .apppushAgreed(user.getApppushAgreed())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Transactional
    public InfoChangeResponseDto updateUserInfo(String usersId, InfoChangeRequestDto dto) {
        UserEntity user = mypageRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (dto.getPhoneNumber() != null &&
                !user.getPhoneNumber().equals(dto.getPhoneNumber()) &&
                mypageRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new BusinessException(ErrorCode.DUPLICATE_PHONE);
        }

        user.updateInfo(dto);

        return InfoChangeResponseDto.builder()
                .id(user.getId())
                .usersId(user.getUsersId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .grade(user.getGrade())
                .major(user.getMajor())
                .profileImage(user.getProfileImage())
                .apppushAgreed(user.getApppushAgreed())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Transactional
    public PwChangeResponseDto pwChange(String usersId, PwChangeRequestDto dto) {
        UserEntity user = mypageRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (!dto.getPassword().equals(dto.getPasswordConfirm())) {
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        user.pwChange(encodedPassword);

        return PwChangeResponseDto.builder()
                .id(user.getId())
                .usersId(user.getUsersId())
                .Message("비밀번호가 성공적으로 변경되었습니다.")
                .build();
    }
}
