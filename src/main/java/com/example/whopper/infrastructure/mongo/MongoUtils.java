package com.example.whopper.infrastructure.mongo;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.stream.Stream;

public interface MongoUtils {
    <T> Stream<T> find(Query query, Class<T> targetClass);
}
