package com.example.whopper.domain.feedback.dto;

public record DeleteFeedbackRequest(
        String element_id,
        String document_id
) {
}
