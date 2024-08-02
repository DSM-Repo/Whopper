package com.example.whopper.domain.feedback.dto;

import java.util.List;

public record MyFeedbackResponse(
        String document_id,
        List<FeedbackResponse> feedback_list
) {
}
