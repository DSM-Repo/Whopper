package com.repo.whopper.application.feedback.service;

import com.repo.whopper.application.feedback.usecase.UpdateFeedbackUseCase;
import com.repo.whopper.domain.feedback.FeedbackRepository;
import com.repo.whopper.interfaces.feedback.dto.request.UpdateFeedbackRequest;
import com.repo.whopper.common.exception.feedback.FeedbackNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateFeedbackService implements UpdateFeedbackUseCase {

    private final FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public void updateFeedback(String feedbackId, UpdateFeedbackRequest request) {
        final var feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> FeedbackNotFoundException.EXCEPTION);

        var newFeedback = feedback.update(request.comment());
        feedbackRepository.save(newFeedback);
    }
}
