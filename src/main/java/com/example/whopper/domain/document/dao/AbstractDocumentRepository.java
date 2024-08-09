package com.example.whopper.domain.document.dao;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import com.example.whopper.domain.document.dto.request.SearchDocumentRequest;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public abstract class AbstractDocumentRepository implements DocumentRepository {
    private final DocumentMongoRepository documentMongoRepository;

    @Override
    public Stream<DocumentEntity> searchDocuments(String name, Integer grade, Integer classNumber, String majorId, String status) {
        return documentMongoRepository.searchDocuments(
                name,
                grade,
                classNumber,
                majorId,
                status
        );
    }

    @Override
    public Optional<DocumentEntity> findById(String id) {
        return documentMongoRepository.findById(id);
    }

    @Override
    public Optional<DocumentEntity> findByWriterId(String id) {
        return documentMongoRepository.findByStudent_Id(id);
    }

    @Override
    public Stream<DocumentEntity> getReleasedDocuments() {
        return documentMongoRepository.findAllByStatus(DocumentStatus.RELEASED);
    }

    @Override
    public DocumentEntity save(DocumentEntity document) {
        return documentMongoRepository.save(document);
    }
}
