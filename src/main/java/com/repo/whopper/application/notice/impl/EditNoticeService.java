package com.repo.whopper.application.notice.impl;

import com.repo.whopper.application.notice.usecase.EditNoticeUseCase;
import com.repo.whopper.common.exception.notice.NoticeNotFoundException;
import com.repo.whopper.domain.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditNoticeService implements EditNoticeUseCase {
    private final NoticeRepository noticeRepository;

    @Override
    @Transactional
    public void editNotice(String noticeId, String title, String content) {
        final var notice = noticeRepository.findById(noticeId)
                .orElseThrow(()-> NoticeNotFoundException.EXCEPTION);

        notice.editNotice(title, content);
        noticeRepository.save(notice);
    }
}
