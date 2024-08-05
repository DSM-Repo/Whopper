package com.example.whopper.domain.document.dao;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.dto.request.SearchDocumentRequest;

import java.util.Optional;
import java.util.stream.Stream;

public interface DocumentRepository {
    Optional<DocumentEntity> findById(String id);
    Optional<DocumentEntity> findByWriterId(String id);
    Stream<DocumentEntity> searchDocument(SearchDocumentRequest request);
    Stream<DocumentEntity> getNotSubmittedDocuments();

    DocumentEntity save(DocumentEntity document);
}
