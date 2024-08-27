package com.example.whopper.domain.library;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

interface LibraryMongoRepository extends MongoRepository<LibraryEntity, String> {
    Optional<LibraryEntity> findFirstByAccessRightNotAndYear(LibraryEntity.AccessRight accessRight, Integer year);
    Optional<LibraryEntity> findFirstByAccessRightNot(LibraryEntity.AccessRight accessRight);
    Optional<LibraryEntity> findFirstByYear(int year);
    Stream<LibraryEntity> findTop3ByOrderByCreateAtDesc();
    Stream<LibraryEntity> findAllByYear(int year);
}
