package com.example.whopper.domain.document.application.base;

import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import com.example.whopper.domain.document.exception.DocumentModificationException;
import com.example.whopper.global.utils.current.CurrentStudent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractUpdateElementServiceBase<T> {
    protected final DocumentRepository documentRepository;
    protected final CurrentStudent currentStudent;

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