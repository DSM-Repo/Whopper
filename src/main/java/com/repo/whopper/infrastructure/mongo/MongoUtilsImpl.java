package com.repo.whopper.infrastructure.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class MongoUtilsImpl implements MongoUtils {
    private final MongoTemplate mongoTemplate;

    @Override
    public <T> Stream<T> find(Query query, Class<T> targetClass) {
        return mongoTemplate.find(query, targetClass).stream();
    }

    @Override
    public <T> BulkOperations bulkOps(Class<T> targetClass) {
        return mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, targetClass);
    }
}
