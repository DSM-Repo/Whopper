package com.example.whopper.lagacy.feedback.dto;

import com.example.whopper.lagacy.document.domain.element.type.DocumentElementType;

public record FeedbackRequest(
        String comment,
        DocumentElementType type,
        String documentId
) {
}
