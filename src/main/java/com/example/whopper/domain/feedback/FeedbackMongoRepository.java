package com.example.whopper.domain.feedback;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.stream.Stream;

public interface FeedbackMongoRepository extends MongoRepository<FeedbackEntity, String> {
    Stream<FeedbackEntity> findAllByResumeIdAndStatus(String resumeId, FeedbackEntity.Status status);
    int countByResumeId(String resumeId);
    void deleteAllByResumeId(String resumeId);
    Stream<FeedbackEntity> findAllByWriterId(String writerId);
    Stream<FeedbackEntity> findAllByResumeIdAndWriterId(String resumeId, String writerId);
    Stream<FeedbackEntity> findAllByResumeIdInAndWriterId(List<String> resumeIds, String writerId);
}
