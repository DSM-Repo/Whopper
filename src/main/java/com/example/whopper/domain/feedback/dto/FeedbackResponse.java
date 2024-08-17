package com.example.whopper.domain.feedback.dto;

import com.example.whopper.domain.feedback.domain.FeedbackEntity;

public record FeedbackResponse(
        String id,
        String element_id,
        String comment,
        String status
) {
    public FeedbackResponse(FeedbackEntity feedback) {
        this(feedback.getId(), feedback.getElementId(), feedback.getComment(), String.valueOf(feedback.getStatus()));
    }
}
