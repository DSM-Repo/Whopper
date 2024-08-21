package com.example.whopper.lagacy.document.application.base;

import com.example.whopper.lagacy.document.dao.DocumentRepository;
import com.example.whopper.lagacy.document.domain.DocumentEntity;
import com.example.whopper.lagacy.document.domain.element.DocumentStatus;
import com.example.whopper.lagacy.document.exception.DocumentModificationException;
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