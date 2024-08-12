package com.example.whopper.domain.document.dao;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import jakarta.annotation.Nullable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface DocumentMongoRepository extends MongoRepository<DocumentEntity, String> {
    Optional<DocumentEntity> findByStudent_Id(String writerId);
    Stream<DocumentEntity> findAllByStatus(DocumentStatus status);
    Stream<DocumentEntity> findAllByWriterGenerationAndYearAndStatus(int generation, int year, DocumentStatus status);

}
