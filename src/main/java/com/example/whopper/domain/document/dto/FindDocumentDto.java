package com.example.whopper.domain.document.dto;

import com.example.whopper.domain.document.domain.element.DocumentStatus;
import com.example.whopper.global.utils.SortDirection;

public record FindDocumentDto(
        String name,
        Integer grade,
        Integer classNumber,
        String major,
        DocumentStatus status,
        String sortBy,
        SortDirection direction
) {
}
