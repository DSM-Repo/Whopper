package com.example.whopper.lagacy.feedback.dto;

import com.example.whopper.lagacy.document.domain.element.type.DocumentElementType;
import com.example.whopper.lagacy.feedback.domain.FeedbackEntity;

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
