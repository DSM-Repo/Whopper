package com.example.whopper.interfaces.feedback.dto.request;

public record FeedbackRequest(
        String comment,
        String type,
        String resumeId
) {
}
