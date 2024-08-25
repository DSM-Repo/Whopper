package com.example.whopper.application.feedback.service;

import com.example.whopper.application.feedback.usecase.AcceptFeedbackUseCase;
import com.example.whopper.domain.feedback.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcceptFeedbackService implements AcceptFeedbackUseCase {
    private final FeedbackRepository feedbackRepository;

    @Override
    public void accept(String id) {
        feedbackRepository.deleteById(id);
    }
}
