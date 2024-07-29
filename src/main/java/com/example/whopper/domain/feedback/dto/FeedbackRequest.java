package com.example.whopper.domain.feedback.dto;

import com.example.whopper.domain.feedback.domain.element.FeedbackType;

public record FeedbackRequest(
        FeedbackType type,
        String content,
        String writer_name,
        String element_id,
        String document_id
) {
}
