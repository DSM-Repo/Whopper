package com.example.whopper.domain.document.dao;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.dto.FindDocumentDto;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository {
    Optional<DocumentEntity> findById(String id);
    Optional<DocumentEntity> findByWriterId(String id);
    List<DocumentEntity> findByDetails(FindDocumentDto dto);
    DocumentEntity save(DocumentEntity document);

}
