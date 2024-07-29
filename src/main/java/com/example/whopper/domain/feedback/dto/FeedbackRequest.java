package com.example.whopper.domain.feedback.dto;

public record FeedbackRequest(
        String content,
        String writer_name,
        String element_id,
        String document_id
) {
}
