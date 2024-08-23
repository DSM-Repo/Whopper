package com.example.whopper.domain.resume;

import java.util.Optional;
import java.util.stream.Stream;

public interface DocumentRepository {
    Optional<ResumeModel> findById(String id);
    Optional<ResumeModel> findByWriterId(String id);
    Stream<ResumeModel> searchDocuments(String name, Integer grade, Integer classNumber, String majorId, String status);
    Stream<ResumeModel> getReleasedDocuments();
    ResumeModel save(ResumeModel resume);
    Stream<ResumeModel> getReleasedDocumentsByGenerationAndYear(int generation, int year);
    Boolean existsByDocumentId(String documentId);
}
