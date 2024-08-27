package com.example.whopper.application.feedback.usecase;

import com.example.whopper.interfaces.feedback.dto.request.UpdateFeedbackRequest;

public interface UpdateFeedbackUseCase {
    void updateFeedback(String feedbackId, UpdateFeedbackRequest request);
}
