package com.example.whopper.domain.resume;

import com.example.whopper.domain.resume.element.DocumentStatus;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
abstract class AbstractDocumentRepository implements DocumentRepository {
    private final DocumentMongoRepository documentMongoRepository;

    @Override
    public Optional<ResumeModel> findById(String id) {
        return documentMongoRepository.findById(id);
    }

    @Override
    public Optional<ResumeModel> findByWriterId(String id) {
        return documentMongoRepository.findByStudentId(id);
    }

    @Override
    public Stream<ResumeModel> getReleasedDocuments() {
        return documentMongoRepository.findAllByStatus(DocumentStatus.RELEASED);
    }

    @Override
    public Stream<ResumeModel> getReleasedDocumentsByGenerationAndYear(int generation, int year) {
        return documentMongoRepository.findAllByWriterGenerationAndYearAndStatus(generation, year, DocumentStatus.RELEASED);
    }

    @Override
    public ResumeModel save(ResumeModel resume) {
        final var entity =

        return documentMongoRepository.save();
    }

    @Override
    public Boolean existsByDocumentId(String documentId) {
        return documentMongoRepository.existsById(documentId);
    }
}
