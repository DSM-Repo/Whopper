package com.example.whopper.interfaces.history.dto;

public record HistoryResponse(
        String id,
        String date,
        String content
) {}