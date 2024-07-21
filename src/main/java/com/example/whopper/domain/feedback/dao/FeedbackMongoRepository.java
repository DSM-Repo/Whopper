package com.example.whopper.domain.feedback.dao;

import com.example.whopper.domain.feedback.domain.FeedbackEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackMongoRepository extends MongoRepository<FeedbackEntity, String> {
}
