package com.example.whopper.domain.document.dao;

import com.example.whopper.domain.document.domain.DocumentEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractDocumentRepository implements DocumentRepository {
    private final DocumentMongoRepository documentMongoRepository;

    @Override
    public Optional<DocumentEntity> findById(String id) {
        return documentMongoRepository.findById(id);
    }

    @Override
    public Optional<DocumentEntity> findByWriterId(String id) {
        return documentMongoRepository.findByStudent_Id(id);
    }

    @Override
    public DocumentEntity save(DocumentEntity document) {
        return documentMongoRepository.save(document);
    }
}
