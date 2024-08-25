package com.example.whopper.domain.history;

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
