package com.example.whopper.application.feedback.service;

import com.example.whopper.application.feedback.usecase.ConfirmFeedbackUseCase;
import com.example.whopper.domain.feedback.FeedbackMongoRepository;
import com.example.whopper.common.exception.feedback.FeedbackNotFoundException;
import com.example.whopper.common.error.exception.ForbiddenException;
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
