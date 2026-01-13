package com.mars7.mars7_recruit_backend.mypage.service;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.common.enums.ErrorCode;
import com.mars7.mars7_recruit_backend.common.exception.BusinessException;
import com.mars7.mars7_recruit_backend.mypage.dto.ChangeRequestDto;
import com.mars7.mars7_recruit_backend.mypage.dto.ChangeResponseDto;
import com.mars7.mars7_recruit_backend.mypage.repository.MypageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MypageRepository mypageRepository;

    @Transactional
    public ChangeResponseDto updateUserInfo(String usersId, ChangeRequestDto dto) {
        UserEntity user = mypageRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (dto.getPhoneNumber() != null &&
                !user.getPhoneNumber().equals(dto.getPhoneNumber()) &&
                mypageRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new BusinessException(ErrorCode.DUPLICATE_PHONE);
        }

        user.updateInfo(dto);

        return ChangeResponseDto.builder()
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
}
