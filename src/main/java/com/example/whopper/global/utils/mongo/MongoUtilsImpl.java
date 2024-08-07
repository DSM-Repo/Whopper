package com.example.whopper.global.utils.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class MongoUtilsImpl implements MongoUtils {
    private final MongoTemplate mongoTemplate;

    public <T> Stream<T> find(Query query, Class<T> targetClass) {
        return mongoTemplate.find(query, targetClass).stream();
    }
}
