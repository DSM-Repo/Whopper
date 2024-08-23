package com.example.whopper.application.feedback.service;

import com.example.whopper.application.feedback.usecase.DeleteFeedbackUseCase;
import com.example.whopper.domain.feedback.FeedbackMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteFeedbackService implements DeleteFeedbackUseCase {
    private final FeedbackMongoRepository feedbackMongoRepository;

    @Override
    public void deleteFeedback(String feedbackId) {
        feedbackMongoRepository.deleteById(feedbackId);
    }
}
