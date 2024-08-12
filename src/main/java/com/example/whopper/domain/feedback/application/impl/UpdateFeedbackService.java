package com.example.whopper.domain.feedback.application.impl;

import com.example.whopper.domain.feedback.application.usecase.UpdateFeedbackUseCase;
import com.example.whopper.domain.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.domain.feedback.domain.FeedbackEntity;
import com.example.whopper.domain.feedback.dto.UpdateFeedbackRequest;
import com.example.whopper.domain.feedback.exception.FeedbackNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateFeedbackService implements UpdateFeedbackUseCase {

    private final FeedbackMongoRepository feedbackMongoRepository;


    @Override
    @Transactional
    public void updateFeedback(UpdateFeedbackRequest request) {
        FeedbackEntity feedback = feedbackMongoRepository.findById(request.id())
                .orElseThrow(()-> FeedbackNotFoundException.EXCEPTION);

        feedback.updateFeedback(request.comment());
        feedbackMongoRepository.save(feedback);
    }
}
