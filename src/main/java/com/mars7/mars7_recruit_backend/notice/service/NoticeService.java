package com.mars7.mars7_recruit_backend.notice.service;

import com.mars7.mars7_recruit_backend.auth.entity.UserEntity;
import com.mars7.mars7_recruit_backend.common.enums.ErrorCode;
import com.mars7.mars7_recruit_backend.common.exception.BusinessException;
import com.mars7.mars7_recruit_backend.mypage.repository.MypageRepository;
import com.mars7.mars7_recruit_backend.notice.dto.NoticeResponseDto;
import com.mars7.mars7_recruit_backend.notice.entity.NoticeEntity;
import com.mars7.mars7_recruit_backend.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final MypageRepository mypageRepository;

    public List<NoticeResponseDto> getNotices(String usersId) {
        // 1. String usersId로 유저 엔티티 조회
        UserEntity user = mypageRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 2. 유저의 고유 번호(Long)로 알림 조회 (레포지토리 메서드명 확인)
        return noticeRepository.findByUserIdOrderByCreatedAtDesc(user.getId())
                .stream()
                .map(NoticeResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void readNotice(Long noticeId, String usersId) {
        // 3. 알림 조회 (에러 코드 정의 확인 필요)
        NoticeEntity notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND)); // 임시로 USER_NOT_FOUND 사용 혹은 정의된 코드 사용

        // 4. 로그인 유저 조회
        UserEntity user = mypageRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 5. 권한 검증 및 업데이트
        if (!notice.getUserId().equals(user.getId())) {
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE); // 접근 권한 에러 코드로 대체
        }

        notice.updateIsRead(true);
    }
}