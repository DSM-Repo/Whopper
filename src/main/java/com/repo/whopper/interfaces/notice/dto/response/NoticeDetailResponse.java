package com.repo.whopper.interfaces.notice.dto.response;

import com.repo.whopper.domain.notice.NoticeModel;

import java.time.LocalDateTime;

public record NoticeDetailResponse(
        String id,
        String title,
        String content,
        String writerName,
        LocalDateTime createdAt,
        Boolean checked
) {
    public NoticeDetailResponse(NoticeModel notice) {
        this(notice.id(), notice.title(), notice.content(), notice.writerName(), notice.createdAt(), notice.checked());
    }
}
