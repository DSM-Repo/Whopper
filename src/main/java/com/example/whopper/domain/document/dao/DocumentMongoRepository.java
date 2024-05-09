package com.example.whopper.domain.document.dao;

import com.example.whopper.domain.document.domain.DocumentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentMongoRepository extends MongoRepository<DocumentEntity, String> {
}
