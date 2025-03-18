package com.dsm.repo.internal.data.repository.library;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

interface LibraryMongoRepository extends MongoRepository<LibraryEntity, String> {
    Stream<LibraryEntity> findTop3ByOrderByCreateAtDesc();
    Stream<LibraryEntity> findAllByYear(int year);

    Optional<LibraryEntity> findByIdAndAccessRight(String id, LibraryEntity.AccessRight accessRight);
}