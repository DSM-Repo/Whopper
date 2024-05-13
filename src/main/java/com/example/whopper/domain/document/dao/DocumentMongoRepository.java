package com.example.whopper.domain.document.dao;

import com.example.whopper.domain.document.domain.DocumentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DocumentMongoRepository extends MongoRepository<DocumentEntity, String> {
    Optional<DocumentEntity> findByWriter_StudentId(String writerId);

}
