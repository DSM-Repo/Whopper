package com.example.whopper.interfaces.feedback.dto;

import com.example.whopper.domain.resume.element.type.DocumentElementType;

public record FeedbackRequest(
        String comment,
        DocumentElementType type,
        String documentId
) {
}
