package com.example.whopper.application.feedback.service;

import com.example.whopper.application.feedback.usecase.UpdateFeedbackUseCase;
import com.example.whopper.domain.feedback.FeedbackRepository;
import com.example.whopper.interfaces.feedback.dto.request.UpdateFeedbackRequest;
import com.example.whopper.common.exception.feedback.FeedbackNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateFeedbackService implements UpdateFeedbackUseCase {

    private final FeedbackRepository feedbackRepository;

    @Override
    public void updateFeedback(String id, UpdateFeedbackRequest request) {
        final var feedback = feedbackRepository.findById(id)
                .orElseThrow(()-> FeedbackNotFoundException.EXCEPTION);

        var newFeedback = feedback.update(request.comment());
        feedbackRepository.save(newFeedback);
    }
}
