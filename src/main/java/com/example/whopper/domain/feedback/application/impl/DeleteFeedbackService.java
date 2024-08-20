package com.example.whopper.domain.feedback.application.impl;

import com.example.whopper.domain.feedback.application.usecase.DeleteFeedbackUseCase;
import com.example.whopper.domain.feedback.dao.FeedbackMongoRepository;
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
