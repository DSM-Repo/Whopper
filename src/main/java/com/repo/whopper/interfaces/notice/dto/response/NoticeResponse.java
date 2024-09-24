package com.repo.whopper.interfaces.notice.dto.response;

import com.repo.whopper.domain.notice.NoticeModel;

import java.time.LocalDateTime;

public record NoticeResponse(
        String id,
        String title,
        String writerName,
        LocalDateTime createdAt,
        Boolean checked
) {
    public static NoticeResponse fromModel(NoticeModel model) {
        return new NoticeResponse(
                model.id(),
                model.title(),
                model.writerName(),
                model.createdAt(),
                model.checked()
        );
    }
}
