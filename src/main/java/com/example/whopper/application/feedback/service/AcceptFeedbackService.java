package com.example.whopper.application.feedback.service;

import com.example.whopper.application.feedback.usecase.AcceptFeedbackUseCase;
import com.example.whopper.domain.feedback.FeedbackMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcceptFeedbackService implements AcceptFeedbackUseCase {
    private final FeedbackMongoRepository feedbackMongoRepository;

    public void accept(String id) {
        feedbackMongoRepository.deleteById(id);
    }
}
