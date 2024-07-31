package com.example.whopper.domain.feedback.dto;

public record FeedbackRequest(
        String comment,
        String element_id,
        String document_id
) {
}
