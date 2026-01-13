package com.mars7.mars7_recruit_backend.auth.service;

import com.mars7.mars7_recruit_backend.auth.dto.*;
import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.auth.repository.UserRepository;
import com.mars7.mars7_recruit_backend.common.config.auth.config.JwtProvider;
import com.mars7.mars7_recruit_backend.common.enums.ErrorCode;
import com.mars7.mars7_recruit_backend.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

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

    //login
    @Transactional
    public LoginResponseDto login(LoginRequestDto requestDto) {
        UserEntity user = userRepository.findByUsersId(requestDto.getUsersId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_INPUT);
        }

        String accessToken = jwtProvider.createAccessToken(user.getUsersId());
        String refreshToken = jwtProvider.createRefreshToken();

        user.updateRefreshToken(refreshToken, LocalDateTime.now().plusDays(14));

        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userDetails(SignupResponseDto.from(user))
                .build();
    }

    //logout
    @Transactional
    public LogoutResponseDto logout(String usersId) {
        UserEntity user = userRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        user.updateRefreshToken(null, null);

        return LogoutResponseDto.builder()
                .id(user.getId())
                .usersId(user.getUsersId())
                .name(user.getName())
                .message("로그아웃되었습니다.")
                .build();
    }
}