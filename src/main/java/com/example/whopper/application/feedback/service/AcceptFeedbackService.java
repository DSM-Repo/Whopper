package com.example.whopper.application.feedback.service;

import com.example.whopper.application.feedback.usecase.AcceptFeedbackUseCase;
import com.example.whopper.domain.feedback.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AcceptFeedbackService implements AcceptFeedbackUseCase {
    private final FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public void accept(String feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }
}
