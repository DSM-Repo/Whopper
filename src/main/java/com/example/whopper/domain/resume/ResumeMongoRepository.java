package com.example.whopper.domain.resume;

import com.example.whopper.domain.resume.element.DocumentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

interface ResumeMongoRepository extends MongoRepository<DocumentEntity, String> {
    Optional<DocumentEntity> findByStudentId(String writerId);
    Stream<DocumentEntity> findAllByStatus(DocumentStatus status);
    Stream<DocumentEntity> findAllByWriterGenerationAndYearAndStatus(int generation, int year, DocumentStatus status);

}
