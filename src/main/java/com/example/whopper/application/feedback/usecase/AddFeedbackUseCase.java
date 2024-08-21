package com.example.whopper.application.feedback.usecase;

import com.example.whopper.interfaces.feedback.dto.FeedbackRequest;

public interface AddFeedbackUseCase {
    void addFeedback(FeedbackRequest request);
}
