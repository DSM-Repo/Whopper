package com.example.whopper.domain.feedback.application.impl;

import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.exception.DocumentNotFoundException;
import com.example.whopper.domain.feedback.application.usecase.UpdateFeedbackUseCase;
import com.example.whopper.domain.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.domain.feedback.domain.FeedbackEntity;
import com.example.whopper.domain.feedback.dto.FeedbackRequest;
import com.example.whopper.domain.feedback.exception.FeedbackNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateFeedbackService implements UpdateFeedbackUseCase {

    private final FeedbackMongoRepository feedbackMongoRepository;

    private final DocumentRepository documentRepository;

    public DocumentEntity findDocument(String documentId) {
        return documentRepository.findById(documentId).orElseThrow(()-> DocumentNotFoundException.EXCEPTION);
    }

    @Transactional
    public void updateFeedback(FeedbackRequest request) {
        FeedbackEntity feedback = feedbackMongoRepository
                .findFirstByDocumentAndElementId(findDocument(request.document_id()), request.element_id())
                .orElseThrow(()-> FeedbackNotFoundException.EXCEPTION);

        feedback.updateFeedback(request.comment());
    }
}
