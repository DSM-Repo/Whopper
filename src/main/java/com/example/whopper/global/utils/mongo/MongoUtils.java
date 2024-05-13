package com.example.whopper.global.utils.mongo;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface MongoUtils {
    <T> List<T> find(Query query, Class<T> targetClass);
}
