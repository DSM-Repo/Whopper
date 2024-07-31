package com.example.whopper.domain.feedback.application.usecase;

import com.example.whopper.domain.feedback.dto.FeedbackRequest;

public interface UpdateFeedbackUseCase {
    void updateFeedback(FeedbackRequest request);
}
