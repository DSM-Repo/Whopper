package com.dsm.repo.internal.core.domain.service.feedback;

import com.dsm.repo.internal.core.usecase.feedback.ConfirmFeedbackUseCase;
import com.dsm.repo.external.exception.domain.feedback.FeedbackNotFoundException;
import com.dsm.repo.external.exception.ForbiddenException;
import com.dsm.repo.internal.core.domain.component.student.CurrentStudent;
import com.dsm.repo.internal.data.repository.feedback.FeedbackRepository;
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
