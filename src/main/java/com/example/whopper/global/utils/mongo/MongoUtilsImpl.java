package com.example.whopper.global.utils.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MongoUtilsImpl {
    private final MongoTemplate mongoTemplate;

    public <T> List<T> find(Query query, Class<T> targetClass) {
        return mongoTemplate.find(query, targetClass);
    }
}
