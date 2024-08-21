package com.example.whopper.lagacy.feedback.dao;

import com.example.whopper.lagacy.feedback.domain.FeedbackEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.stream.Stream;

public interface FeedbackMongoRepository extends MongoRepository<FeedbackEntity, String> {
    List<FeedbackEntity> findAllByDocumentId(String documentId);
    int countByDocumentId(String documentId);
    void deleteAllByDocumentId(String documentId);
    Stream<FeedbackEntity> findAllByTeacherId(String teacherId);
}
