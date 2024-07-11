package com.example.whopper.domain.document.application.base;

import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.global.utils.current.CurrentUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractUpdateElementServiceBase<T> implements UpdateElementUseCase<T> {
    protected final DocumentRepository documentRepository;
    protected final CurrentUser currentUser;

    @Override
    public void update(T request) {
        var document = currentUser.getDocument();
        updateDocument(document, request);
        documentRepository.save(document);
    }

    protected abstract void updateDocument(DocumentEntity document, T request);
}