package com.repo.whopper.domain.major;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MajorMongoRepository extends MongoRepository<MajorEntity, String> {
    boolean existsByName(String name);
    Optional<MajorEntity> findByName(String name);
}
