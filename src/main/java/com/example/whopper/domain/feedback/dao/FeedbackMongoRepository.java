package com.example.whopper.domain.feedback.dao;

import com.example.whopper.domain.feedback.domain.FeedbackEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedbackMongoRepository extends MongoRepository<FeedbackEntity, String> {
    List<FeedbackEntity> findAllByDocumentId(String documentId);
    int countByDocumentId(String documentId);
    void deleteAllByDocumentId(String documentId);
}
