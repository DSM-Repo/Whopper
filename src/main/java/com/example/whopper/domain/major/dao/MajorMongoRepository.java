package com.example.whopper.domain.major.dao;

import com.example.whopper.domain.major.domain.MajorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MajorMongoRepository extends MongoRepository<MajorEntity, String> {
    boolean existsByName(String name);
}
