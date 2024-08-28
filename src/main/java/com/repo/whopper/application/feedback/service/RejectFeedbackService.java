package com.repo.whopper.application.feedback.service;

import com.repo.whopper.application.feedback.usecase.RejectFeedbackUseCase;
import com.repo.whopper.common.exception.feedback.FeedbackNotFoundException;
import com.repo.whopper.domain.feedback.FeedbackRepository;
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
