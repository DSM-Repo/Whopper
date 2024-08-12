package com.example.whopper.domain.feedback.dto;

public record UpdateFeedbackRequest(
        String id,
        String comment
) {
}
