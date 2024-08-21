package com.example.whopper.lagacy.feedback.application.usecase;

import com.example.whopper.lagacy.feedback.dto.UpdateFeedbackRequest;

public interface UpdateFeedbackUseCase {
    void updateFeedback(String id, UpdateFeedbackRequest request);
}
