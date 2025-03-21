package com.dsm.repo.internal.data.repository.feedback;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.stream.Stream;

public interface FeedbackMongoRepository extends MongoRepository<FeedbackEntity, String> {
    Stream<FeedbackEntity> findAllByResumeIdAndStatus(String resumeId, FeedbackEntity.Status status);
    void deleteAllByResumeId(String resumeId);
    Stream<FeedbackEntity> findAllByWriterId(String writerId);
    Stream<FeedbackEntity> findAllByResumeIdAndWriterId(String resumeId, String writerId);
    Stream<FeedbackEntity> findAllByResumeIdInAndWriterId(List<String> resumeIds, String writerId);
}
