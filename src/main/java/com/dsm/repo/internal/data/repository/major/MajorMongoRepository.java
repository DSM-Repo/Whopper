package com.dsm.repo.internal.data.repository.major;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MajorMongoRepository extends MongoRepository<MajorEntity, String> {
    Optional<MajorEntity> findById(String name);
}
