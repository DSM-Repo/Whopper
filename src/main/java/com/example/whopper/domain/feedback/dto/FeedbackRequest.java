package com.example.whopper.domain.feedback.dto;

import com.example.whopper.domain.document.domain.element.type.DocumentElementType;

public record FeedbackRequest(
        String comment,
        DocumentElementType type,
        String documentId
) {
}
