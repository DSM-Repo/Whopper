package com.example.whopper.domain.document.dao;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.dto.request.SearchDocumentRequest;

import java.util.Optional;
import java.util.stream.Stream;

public interface DocumentRepository {
    Optional<DocumentEntity> findById(String id);
    Optional<DocumentEntity> findByWriterId(String id);
    Stream<DocumentEntity> searchDocuments(String name, Integer grade, Integer classNumber, String majorId, String status);
    Stream<DocumentEntity> getReleasedDocuments();
    DocumentEntity save(DocumentEntity document);
    Stream<DocumentEntity> getReleasedDocumentsByGenerationAndYear(int generation, int year);
    Boolean existsByDocumentId(String documentId);
}
