package com.example.whopper.application.feedback.service;

import com.example.whopper.application.feedback.usecase.RejectFeedbackUseCase;
import com.example.whopper.common.exception.feedback.FeedbackNotFoundException;
import com.example.whopper.domain.feedback.FeedbackMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectFeedbackService implements RejectFeedbackUseCase {
    private final FeedbackMongoRepository feedbackMongoRepository;

    public void reject(String id) {
        var feedback = feedbackMongoRepository.findById(id)
                .orElseThrow(() -> FeedbackNotFoundException.EXCEPTION);

        feedback.rejected();

        feedbackMongoRepository.save(feedback);
    }
}
