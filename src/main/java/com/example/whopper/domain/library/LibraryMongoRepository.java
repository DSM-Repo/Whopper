package com.example.whopper.domain.library;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.stream.Stream;

interface LibraryMongoRepository extends MongoRepository<LibraryEntity, String> {
    Stream<LibraryEntity> findTop3ByOrderByCreateAtDesc();
    Stream<LibraryEntity> findAllByYear(int year);
}