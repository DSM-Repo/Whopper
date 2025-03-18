package com.dsm.repo.internal.core.usecase.feedback;

import com.dsm.repo.external.web.rest.feedback.dto.request.UpdateFeedbackRequest;

public interface UpdateFeedbackUseCase {
    void updateFeedback(String feedbackId, UpdateFeedbackRequest request);
}
