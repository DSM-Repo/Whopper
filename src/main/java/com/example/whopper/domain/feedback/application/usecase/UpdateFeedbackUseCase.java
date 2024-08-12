package com.example.whopper.domain.feedback.application.usecase;

import com.example.whopper.domain.feedback.dto.UpdateFeedbackRequest;

public interface UpdateFeedbackUseCase {
    void updateFeedback(UpdateFeedbackRequest request);
}
