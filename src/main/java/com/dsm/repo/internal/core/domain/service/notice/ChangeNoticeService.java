package com.dsm.repo.internal.core.domain.service.notice;

import com.dsm.repo.internal.core.usecase.notice.ChangeNoticeUseCase;
import com.dsm.repo.external.exception.domain.notice.NoticeNotFoundException;
import com.dsm.repo.internal.core.domain.model.notice.NoticeModel;
import com.dsm.repo.internal.data.repository.notice.NoticeRepository;
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
