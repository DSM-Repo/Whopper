package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.usecase.ReleaseDocumentUseCase;
import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.element.DocumentStatus;
import com.example.whopper.common.exception.resume.DocumentIllegalStatusException;
import com.example.whopper.common.exception.resume.DocumentNotFoundException;
import com.example.whopper.domain.feedback.FeedbackMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class ReleaseDocumentService implements ReleaseDocumentUseCase {
    private final ResumeRepository resumeRepository;
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
        return resumeRepository.findById(documentId)
                .orElseThrow(() -> DocumentNotFoundException.EXCEPTION);
    }

    private void release(DocumentEntity document) {
        document.release();
    }

    private void cancelRelease(DocumentEntity document) {
        document.submit();
    }

    private void save(DocumentEntity document) {
        resumeRepository.save(document);
    }
}
