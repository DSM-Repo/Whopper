package com.dsm.repo.internal.core.domain.model.history;

import lombok.Builder;

@Builder
public record HistoryModel(
        String id,
        String date,
        String content
) {
    public HistoryModel(String date, String content) {
        this(null, date, content);
    }

    public HistoryModel update(String date, String content) {
        return new HistoryModel(id, date, content);
    }
}
