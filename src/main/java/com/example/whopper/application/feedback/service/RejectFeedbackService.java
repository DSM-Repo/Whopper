package com.example.whopper.application.feedback.service;

import com.example.whopper.application.feedback.usecase.RejectFeedbackUseCase;
import com.example.whopper.common.exception.feedback.FeedbackNotFoundException;
import com.example.whopper.domain.feedback.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectFeedbackService implements RejectFeedbackUseCase {
    private final FeedbackRepository feedbackRepository;

    @Override
    public void reject(String id) {
        final var feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> FeedbackNotFoundException.EXCEPTION);

        feedback.reject();

        feedbackRepository.save(feedback);
    }
}
