package com.example.whopper.application.feedback.usecase;

import com.example.whopper.interfaces.feedback.dto.UpdateFeedbackRequest;

public interface UpdateFeedbackUseCase {
    void updateFeedback(String id, UpdateFeedbackRequest request);
}
