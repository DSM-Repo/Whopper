package com.dsm.repo.internal.core.usecase.feedback;

import com.dsm.repo.external.web.rest.feedback.dto.request.FeedbackRequest;

public interface AddFeedbackUseCase {
    void addFeedback(FeedbackRequest request);
}
