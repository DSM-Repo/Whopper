package com.repo.whopper.domain.major;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MajorMongoRepository extends MongoRepository<MajorEntity, String> {
    Optional<MajorEntity> findById(String name);
}
