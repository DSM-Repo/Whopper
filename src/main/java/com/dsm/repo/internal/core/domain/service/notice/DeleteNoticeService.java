package com.dsm.repo.internal.core.domain.service.notice;

import com.dsm.repo.internal.core.usecase.notice.DeleteNoticeUseCase;
import com.dsm.repo.internal.data.repository.notice.NoticeRepository;
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
