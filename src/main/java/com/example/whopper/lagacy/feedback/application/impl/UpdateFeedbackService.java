package com.example.whopper.lagacy.feedback.application.impl;

import com.example.whopper.lagacy.feedback.application.usecase.UpdateFeedbackUseCase;
import com.example.whopper.lagacy.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.lagacy.feedback.domain.FeedbackEntity;
import com.example.whopper.lagacy.feedback.dto.UpdateFeedbackRequest;
import com.example.whopper.lagacy.feedback.exception.FeedbackNotFoundException;
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
        FeedbackEntity feedback = feedbackMongoRepository.findById(id)
                .orElseThrow(()-> FeedbackNotFoundException.EXCEPTION);

        feedback.updateFeedback(request.comment());
        feedbackMongoRepository.save(feedback);
    }
}
