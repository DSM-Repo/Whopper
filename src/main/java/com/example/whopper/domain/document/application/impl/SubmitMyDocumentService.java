package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.usecase.SubmitMyDocumentUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import com.example.whopper.global.utils.current.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmitMyDocumentService implements SubmitMyDocumentUseCase {
    private final DocumentRepository documentRepository;
    private final CurrentUser currentUser;

    @Override
    public void submit() {
        updateDocumentStatusAndSave(currentUser.getDocument());
    }

    private void updateDocumentStatusAndSave(DocumentEntity doc) {
        doc.updateDocumentStatus(DocumentStatus.SUBMITTED);
        documentRepository.save(doc);
    }
}
