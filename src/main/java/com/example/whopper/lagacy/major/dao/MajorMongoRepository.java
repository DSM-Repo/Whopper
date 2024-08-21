package com.example.whopper.lagacy.major.dao;

import com.example.whopper.lagacy.major.domain.MajorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MajorMongoRepository extends MongoRepository<MajorEntity, String> {
    boolean existsByName(String name);
    Optional<MajorEntity> findByName(String name);
}
