package com.example.whopper.application.resume.base;

import com.example.whopper.domain.resume.DocumentRepository;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.element.DocumentStatus;
import com.example.whopper.common.exception.resume.DocumentModificationException;
import com.example.whopper.global.utils.current.CurrentStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public abstract class AbstractUpdateElementServiceBase<T> {
    protected final DocumentRepository documentRepository;
    protected final CurrentStudent currentStudent;

    @Transactional
    public void update(T request) {
        var document = currentStudent.getDocument();

        if (!document.getStatus().equals(DocumentStatus.ONGOING)) {
            throw DocumentModificationException.EXCEPTION;
        }

        updateDocument(document, request);
        documentRepository.save(document);
    }

    protected abstract void updateDocument(DocumentEntity document, T request);
}