package com.repo.whopper.application.feedback.usecase;

import com.repo.whopper.interfaces.feedback.dto.request.UpdateFeedbackRequest;

public interface UpdateFeedbackUseCase {
    void updateFeedback(String feedbackId, UpdateFeedbackRequest request);
}
