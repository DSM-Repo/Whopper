package com.example.whopper.domain.document.dto.request;

public record SearchDocumentRequest(
        String name,
        Integer grade,
        Integer classNumber,
        String majorId,
        String status
) {
}
