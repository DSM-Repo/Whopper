package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.usecase.SubmitMyDocumentUseCase;
import com.example.whopper.domain.resume.DocumentRepository;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.element.DocumentStatus;
import com.example.whopper.application.student.component.CurrentStudent;
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
