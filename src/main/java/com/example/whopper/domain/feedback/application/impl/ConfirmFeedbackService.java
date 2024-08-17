package com.example.whopper.domain.feedback.application.impl;

import com.example.whopper.domain.feedback.application.usecase.ConfirmFeedbackUseCase;
import com.example.whopper.domain.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.domain.feedback.exception.FeedbackNotFoundException;
import com.example.whopper.global.error.exception.ForbiddenException;
import com.example.whopper.global.utils.current.CurrentStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmFeedbackService implements ConfirmFeedbackUseCase {
    private final FeedbackMongoRepository feedbackMongoRepository;
    private final CurrentStudent currentStudent;

    @Override
    public void confirm(String id) {
        var feedback = feedbackMongoRepository.findById(id)
                .orElseThrow(() -> FeedbackNotFoundException.EXCEPTION);

        var currentDocument = currentStudent.getDocument();

        if (!currentDocument.getId().equals(feedback.getDocumentId())) {
            throw ForbiddenException.EXCEPTION;
        }

        feedback.confirmed();
        feedbackMongoRepository.save(feedback);
    }
}
