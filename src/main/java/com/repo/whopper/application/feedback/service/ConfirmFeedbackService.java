package com.repo.whopper.application.feedback.service;

import com.repo.whopper.application.feedback.usecase.ConfirmFeedbackUseCase;
import com.repo.whopper.common.exception.feedback.FeedbackNotFoundException;
import com.repo.whopper.common.error.exception.ForbiddenException;
import com.repo.whopper.application.student.component.CurrentStudent;
import com.repo.whopper.domain.feedback.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConfirmFeedbackService implements ConfirmFeedbackUseCase {
    private final FeedbackRepository feedbackRepository;
    private final CurrentStudent currentStudent;

    @Override
    @Transactional
    public void confirm(String feedbackId) {
        final var feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> FeedbackNotFoundException.EXCEPTION);

        final var resume = currentStudent.getResume();

        if (!resume.id().equals(feedback.resumeId())) {
            throw ForbiddenException.EXCEPTION;
        }

        final var newFeedback = feedback.confirm();
        feedbackRepository.save(newFeedback);
    }
}
