package com.example.whopper.domain.feedback;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.stream.Stream;

public interface FeedbackMongoRepository extends MongoRepository<FeedbackEntity, String> {
    List<FeedbackEntity> findAllByDocumentIdAndStatus(String documentId, FeedbackEntity.Status status);
    int countByDocumentId(String documentId);
    void deleteAllByDocumentId(String documentId);
    Stream<FeedbackEntity> findAllByTeacherId(String teacherId);
    Stream<FeedbackEntity> findAllByDocumentIdAndTeacherId(String documentId, String teacherId);
    Stream<FeedbackEntity> findAllByDocumentIdInAndTeacherId(List<String> documentIds, String teacherId);
}
