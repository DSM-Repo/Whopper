package com.dsm.repo.internal.core.domain.service.feedback;

import com.dsm.repo.internal.core.usecase.feedback.RejectFeedbackUseCase;
import com.dsm.repo.external.exception.domain.feedback.FeedbackNotFoundException;
import com.dsm.repo.internal.data.repository.feedback.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RejectFeedbackService implements RejectFeedbackUseCase {
    private final FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public void reject(String feedbackId) {
        final var feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> FeedbackNotFoundException.EXCEPTION);

        var newFeedback = feedback.reject();

        feedbackRepository.save(newFeedback);
    }
}
