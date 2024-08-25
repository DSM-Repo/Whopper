package com.example.whopper.application.feedback.service;

import com.example.whopper.application.feedback.usecase.UpdateFeedbackUseCase;
import com.example.whopper.domain.feedback.FeedbackMongoRepository;
import com.example.whopper.domain.feedback.FeedbackEntity;
import com.example.whopper.interfaces.feedback.dto.request.UpdateFeedbackRequest;
import com.example.whopper.common.exception.feedback.FeedbackNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateFeedbackService implements UpdateFeedbackUseCase {

    private final FeedbackMongoRepository feedbackMongoRepository;


    @Override
    @Transactional
    public void updateFeedback(String id, UpdateFeedbackRequest request) {
        var feedback = feedbackMongoRepository.findById(id)
                .orElseThrow(()-> FeedbackNotFoundException.EXCEPTION);

        feedback.updateFeedback(request.comment());
        feedbackMongoRepository.save(feedback);
    }
}
