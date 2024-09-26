package com.repo.whopper.application.notice.impl;

import com.repo.whopper.application.notice.usecase.ChangeNoticeUseCase;
import com.repo.whopper.common.exception.notice.NoticeNotFoundException;
import com.repo.whopper.domain.notice.NoticeModel;
import com.repo.whopper.domain.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangeNoticeService implements ChangeNoticeUseCase {
    private final NoticeRepository noticeRepository;

    @Override
    @Transactional
    public void checkNotice(String noticeId) {
        NoticeModel notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        var newNotice = notice.checkNotice();
        noticeRepository.save(newNotice);
    }

    @Override
    @Transactional
    public void editNotice(String noticeId, String title, String content) {
        final var notice = noticeRepository.findById(noticeId)
                .orElseThrow(()-> NoticeNotFoundException.EXCEPTION);

        var newNotice = notice.editNotice(title, content);
        noticeRepository.save(newNotice);
    }
}
