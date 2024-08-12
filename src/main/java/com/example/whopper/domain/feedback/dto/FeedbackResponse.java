package com.example.whopper.domain.feedback.dto;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.feedback.domain.FeedbackEntity;

public record FeedbackResponse(
        String id,
        String element_id,
        String element_name,
        String comment
) {
    public FeedbackResponse(FeedbackEntity feedback, String elementName) {
        this(feedback.getId(), feedback.getElementId(), elementName, feedback.getComment());
    }
}
