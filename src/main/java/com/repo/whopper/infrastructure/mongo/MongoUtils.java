package com.repo.whopper.infrastructure.mongo;

import org.springframework.data.mongodb.core.query.Query;

import java.util.stream.Stream;

public interface MongoUtils {
    <T> Stream<T> find(Query query, Class<T> targetClass);
}
