package com.example.whopper.domain.feedback.dto;

import com.example.whopper.domain.document.domain.element.type.DocumentElementType;
import com.example.whopper.domain.feedback.domain.FeedbackEntity;

public record FeedbackResponse(
        String id,
        DocumentElementType type,
        String comment,
        String status
) {
    public FeedbackResponse(FeedbackEntity feedback) {
        this(feedback.getId(), feedback.getType(), feedback.getComment(), String.valueOf(feedback.getStatus()));
    }
}
