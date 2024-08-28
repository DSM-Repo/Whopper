package com.repo.whopper.application.feedback.usecase;

import com.repo.whopper.interfaces.feedback.dto.request.FeedbackRequest;

public interface AddFeedbackUseCase {
    void addFeedback(FeedbackRequest request);
}
