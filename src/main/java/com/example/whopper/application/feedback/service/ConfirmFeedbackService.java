package com.example.whopper.application.feedback.service;

import com.example.whopper.application.feedback.usecase.ConfirmFeedbackUseCase;
import com.example.whopper.common.exception.feedback.FeedbackNotFoundException;
import com.example.whopper.common.error.exception.ForbiddenException;
import com.example.whopper.application.student.component.CurrentStudent;
import com.example.whopper.domain.feedback.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmFeedbackService implements ConfirmFeedbackUseCase {
    private final FeedbackRepository feedbackRepository;
    private final CurrentStudent currentStudent;

    @Override
    public void confirm(String id) {
        final var feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> FeedbackNotFoundException.EXCEPTION);

        final var resume = currentStudent.getResume();

        if (!resume.id().equals(feedback.id())) {
            throw ForbiddenException.EXCEPTION;
        }

        feedback.confirm();
        feedbackRepository.save(feedback);
    }
}
