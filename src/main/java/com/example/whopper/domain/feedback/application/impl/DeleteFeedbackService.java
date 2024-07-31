package com.example.whopper.domain.feedback.application.impl;

import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.exception.DocumentNotFoundException;
import com.example.whopper.domain.feedback.application.usecase.DeleteFeedbackUseCase;
import com.example.whopper.domain.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.domain.feedback.dto.DeleteFeedbackRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteFeedbackService implements DeleteFeedbackUseCase {

    private final DocumentRepository documentRepository;

    private final FeedbackMongoRepository feedbackMongoRepository;

    public DocumentEntity findDocument(String documentId) {
        return documentRepository.findById(documentId).orElseThrow(()-> DocumentNotFoundException.EXCEPTION);
    }

    public void deleteFeedback(DeleteFeedbackRequest request) {
        feedbackMongoRepository.deleteByDocumentAndElementId(findDocument(request.document_id()), request.element_id());
    }
}
