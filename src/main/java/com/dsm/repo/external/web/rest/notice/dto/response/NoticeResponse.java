package com.dsm.repo.external.web.rest.notice.dto.response;

import com.dsm.repo.internal.core.domain.model.notice.NoticeModel;

import java.time.LocalDateTime;

public record NoticeResponse(
        String id,
        String title,
        String content,
        String writerName,
        LocalDateTime createdAt,
        Boolean checked
) {
    public static NoticeResponse fromModel(NoticeModel model) {
        return new NoticeResponse(
                model.id(),
                model.title(),
                model.content(),
                model.writerName(),
                model.createdAt(),
                model.checked()
        );
    }
}
