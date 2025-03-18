package com.dsm.repo.internal.core.domain.service.notice;

import com.dsm.repo.internal.core.usecase.notice.WriteNoticeUseCase;
import com.dsm.repo.internal.core.domain.event.teacher.CurrentTeacher;
import com.dsm.repo.internal.core.domain.model.notice.NoticeModel;
import com.dsm.repo.internal.data.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WriteNoticeService implements WriteNoticeUseCase {
    private final NoticeRepository noticeRepository;
    private final CurrentTeacher currentTeacher;

    @Override
    @Transactional
    public void writeNotice(String title, String content) {
        LocalDateTime now = LocalDateTime.now();

        final var teacher = currentTeacher.getTeacher();

        noticeRepository.save(new NoticeModel(null, title, content, teacher.name(), now, false));
    }
}