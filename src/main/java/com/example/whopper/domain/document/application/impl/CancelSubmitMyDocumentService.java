package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.usecase.CancelSubmitMyDocumentUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import com.example.whopper.global.utils.current.CurrentStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelSubmitMyDocumentService implements CancelSubmitMyDocumentUseCase {
    private final DocumentRepository documentRepository;
    private final CurrentStudent currentStudent;

    @Override
    public void cancel() {
        updateDocumentStatusAndSave(currentStudent.getDocument());
    }

    private void updateDocumentStatusAndSave(DocumentEntity document) {
        document.updateDocumentStatus(DocumentStatus.ONGOING);
        documentRepository.save(document);
    }
}
