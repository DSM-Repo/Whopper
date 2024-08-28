package com.repo.whopper.interfaces.history.dto;

public record HistoryResponse(
        String id,
        String date,
        String content
) {}