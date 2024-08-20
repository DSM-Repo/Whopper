package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.usecase.SubmitMyDocumentUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import com.example.whopper.global.utils.current.CurrentStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class SubmitMyDocumentService implements SubmitMyDocumentUseCase {
    private final DocumentRepository documentRepository;
    private final CurrentStudent currentStudent;

    @Override
    public void submit() {
        var document = currentStudent.getDocument();

        if (document.getStatus().equals(DocumentStatus.SUBMITTED)) {
            cancelSubmit(document);
        } else {
            submit(document);
        }

        save(document);
    }

    private void cancelSubmit(DocumentEntity document) {
        document.onGoing();
    }

    private void submit(DocumentEntity document) {
        document.submit();
    }

    private void save(DocumentEntity document) {
        documentRepository.save(document);
    }
}
