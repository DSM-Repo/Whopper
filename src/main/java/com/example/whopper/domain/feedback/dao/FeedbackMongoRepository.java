package com.example.whopper.domain.feedback.dao;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.feedback.domain.FeedbackEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackMongoRepository extends MongoRepository<FeedbackEntity, String> {
    Optional<FeedbackEntity> findFirstByDocumentAndElementId(DocumentEntity document, String elementId);
    void deleteByDocumentAndElementId(DocumentEntity document, String elementId);
    List<FeedbackEntity> findAllByDocument(DocumentEntity document);
    int countByDocument(DocumentEntity document);
    void deleteAllByDocument(DocumentEntity document);
}
