package com.example.whopper.domain.feedback.dto;

import com.example.whopper.domain.feedback.domain.element.FeedbackType;

public record FeedbackRequest(
        FeedbackType type,
        String content,
        String writerName,
        String documentId
) {
}
