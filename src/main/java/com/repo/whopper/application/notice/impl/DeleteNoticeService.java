package com.repo.whopper.application.notice.impl;

import com.repo.whopper.application.notice.usecase.DeleteNoticeUseCase;
import com.repo.whopper.domain.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteNoticeService implements DeleteNoticeUseCase {
    private final NoticeRepository noticeRepository;

    @Override
    @Transactional
    public void deleteNotice(String noticeId) {
        noticeRepository.deleteById(noticeId);
    }
}
