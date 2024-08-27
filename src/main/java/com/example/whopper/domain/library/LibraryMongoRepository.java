package com.example.whopper.domain.library;

import com.example.whopper.domain.library.type.AccessRight;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface LibraryMongoRepository extends MongoRepository<LibraryEntity, String> {
    Optional<LibraryEntity> findFirstByAccessRightNotAndYear(AccessRight accessRight, int year);
    Optional<LibraryEntity> findFirstByAccessRightNot(AccessRight accessRight);
    Optional<LibraryEntity> findFirstByYear(int year);
    Stream<LibraryEntity> findTop3ByOrderByCreateAtDesc();

    List<LibraryEntity> findAllByYear(int year);
}
