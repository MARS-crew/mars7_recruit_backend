package com.mars7.mars7_recruit_backend.auth.service;

import com.mars7.mars7_recruit_backend.auth.dto.SignupRequestDto;
import com.mars7.mars7_recruit_backend.auth.dto.SignupResponseDto;
import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.auth.repository.UserRepository;
import com.mars7.mars7_recruit_backend.common.enums.ErrorCode;
import com.mars7.mars7_recruit_backend.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    //signup
    @Transactional
    public SignupResponseDto signUp(SignupRequestDto requestDto) {
        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())) {
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }

        if (userRepository.existsByUsersId(requestDto.getUsersId())) {
            throw new BusinessException(ErrorCode.DUPLICATE_USER);
        }

        if(userRepository.existsByPhoneNumber(requestDto.getPhoneNumber())){
            throw new BusinessException(ErrorCode.DUPLICATE_PHONE);
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        UserEntity user = UserEntity.builder()
                .usersId(requestDto.getUsersId())
                .password(encodedPassword)
                .name(requestDto.getName())
                .phoneNumber(requestDto.getPhoneNumber())
                .grade(requestDto.getGrade())
                .major(requestDto.getMajor())
                .gender(requestDto.getGender())
                .birth(requestDto.getBirth())
                .profileImage(requestDto.getProfileImage() != null ? requestDto.getProfileImage() : "https://www.flaticon.com/kr/free-icon/profile_6522516")
                .address(requestDto.getAddress())
                .serviceAgreed(requestDto.getServiceAgreed())
                .apppushAgreed(requestDto.getApppushAgreed())
                .build();

        UserEntity savedUser = userRepository.save(user);

        return SignupResponseDto.from(savedUser);
    }

    //id check
    @Transactional(readOnly = true)
    public String checkUsersIdDuplicate(String usersId) {
        if (userRepository.existsByUsersId(usersId)) {
            throw new BusinessException(ErrorCode.DUPLICATE_USER);
        }
        return "사용 가능한 아이디입니다.";
    }
}