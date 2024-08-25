package com.example.whopper.interfaces.feedback.dto;

public record FeedbackRequest(
        String comment,
        String type,
        String documentId
) {
}
