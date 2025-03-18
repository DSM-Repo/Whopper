package com.dsm.repo.internal.core.domain.service.feedback;

import com.dsm.repo.internal.core.usecase.feedback.AcceptFeedbackUseCase;
import com.dsm.repo.internal.data.repository.feedback.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AcceptFeedbackService implements AcceptFeedbackUseCase {
    private final FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public void accept(String feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }
}
