package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.usecase.ReleaseDocumentUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import com.example.whopper.domain.document.exception.DocumentIllegalStatusException;
import com.example.whopper.domain.document.exception.DocumentNotFoundException;
import com.example.whopper.domain.feedback.dao.FeedbackMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReleaseDocumentService implements ReleaseDocumentUseCase {
    private final DocumentRepository documentRepository;
    private final FeedbackMongoRepository feedbackMongoRepository;

    @Override
    public void release(String documentId) {
        var document = findById(documentId);

        if (document.getStatus().equals(DocumentStatus.RELEASED)) {
            cancelRelease(document);
        } else if (document.getStatus().equals(DocumentStatus.SUBMITTED)) {
            deleteFeedback(documentId);
            release(document);
        } else {
            throw DocumentIllegalStatusException.EXCEPTION;
        }

        save(document);
    }

    private void deleteFeedback(String documentId) {
        feedbackMongoRepository.deleteAllByDocumentId(documentId);
    }

    private DocumentEntity findById(String documentId) {
        return documentRepository.findById(documentId)
                .orElseThrow(() -> DocumentNotFoundException.EXCEPTION);
    }

    private void release(DocumentEntity document) {
        document.release();
    }

    private void cancelRelease(DocumentEntity document) {
        document.submit();
    }

    private void save(DocumentEntity document) {
        documentRepository.save(document);
    }
}
