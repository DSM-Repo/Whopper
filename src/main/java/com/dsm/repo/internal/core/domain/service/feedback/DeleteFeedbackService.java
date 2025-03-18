package com.dsm.repo.internal.core.domain.service.feedback;

import com.dsm.repo.internal.core.usecase.feedback.DeleteFeedbackUseCase;
import com.dsm.repo.internal.data.repository.feedback.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteFeedbackService implements DeleteFeedbackUseCase {
    private final FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public void deleteFeedback(String feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }
}
