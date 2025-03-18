package com.dsm.repo.external.web.rest.history.dto;

public record HistoryResponse(
        String id,
        String date,
        String content
) {}