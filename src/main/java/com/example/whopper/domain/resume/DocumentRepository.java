package com.example.whopper.domain.resume;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface DocumentRepository {
    Optional<DocumentEntity> findById(String id);
    Optional<DocumentEntity> findByWriterId(String id);
    List<DocumentEntity> searchDocuments(String name, Integer grade, Integer classNumber, String majorId, String status);
    Stream<DocumentEntity> getReleasedDocuments();
    DocumentEntity save(DocumentEntity document);
    Stream<DocumentEntity> getReleasedDocumentsByGenerationAndYear(int generation, int year);
    Boolean existsByDocumentId(String documentId);
}
