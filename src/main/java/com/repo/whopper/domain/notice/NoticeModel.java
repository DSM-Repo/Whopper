package com.repo.whopper.domain.notice;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NoticeModel(
        String id,
        String title,
        String content,
        String writerName,
        LocalDateTime createdAt,
        Boolean checked
) {
    public NoticeModel checkNotice() {
        return new NoticeModel(id, title, content, writerName, createdAt, true);
    }

    public NoticeModel editNotice(String title, String content) {
        return new NoticeModel(id, title, content, writerName, createdAt, checked);
    }
}
