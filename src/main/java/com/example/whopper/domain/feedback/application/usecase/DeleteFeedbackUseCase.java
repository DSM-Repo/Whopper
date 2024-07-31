package com.example.whopper.domain.feedback.application.usecase;

import com.example.whopper.domain.feedback.dto.DeleteFeedbackRequest;

public interface DeleteFeedbackUseCase {
    void deleteFeedback(DeleteFeedbackRequest request);
}
