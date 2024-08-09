package com.example.whopper.domain.library.dao;

import com.example.whopper.domain.library.domain.LibraryEntity;
import com.example.whopper.domain.library.domain.type.AccessRight;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LibraryMongoRepository extends MongoRepository<LibraryEntity, String> {
    Optional<LibraryEntity> findFirstByAccessRightNotAndYear(AccessRight accessRight, int year);
    Optional<LibraryEntity> findFirstByYear(int year);
}
