package com.repo.whopper.application.notice.impl;

import com.repo.whopper.application.notice.usecase.WriteNoticeUseCase;
import com.repo.whopper.application.teacher.component.CurrentTeacher;
import com.repo.whopper.domain.notice.NoticeModel;
import com.repo.whopper.domain.notice.NoticeRepository;
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