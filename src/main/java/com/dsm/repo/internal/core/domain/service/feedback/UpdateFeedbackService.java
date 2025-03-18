package com.dsm.repo.internal.core.domain.service.feedback;

import com.dsm.repo.internal.core.usecase.feedback.UpdateFeedbackUseCase;
import com.dsm.repo.internal.data.repository.feedback.FeedbackRepository;
import com.dsm.repo.external.web.rest.feedback.dto.request.UpdateFeedbackRequest;
import com.dsm.repo.external.exception.domain.feedback.FeedbackNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateFeedbackService implements UpdateFeedbackUseCase {

    private final FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public void updateFeedback(String feedbackId, UpdateFeedbackRequest request) {
        final var feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> FeedbackNotFoundException.EXCEPTION);

        var newFeedback = feedback.update(request.comment());
        feedbackRepository.save(newFeedback);
    }
}
