package com.example.whopper.interfaces.resume.dto.request;

public record SearchDocumentRequest(
        String name,
        Integer grade,
        Integer classNumber,
        String majorId,
        String status
) {
}
