package com.example.whopper.domain.history;

public record HistoryModel(
        String id,
        String date,
        String content
) {
    public HistoryModel update(String date, String content) {
        return new HistoryModel(id, date, content);
    }
}
