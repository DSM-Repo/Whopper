package com.example.whopper.domain.library;

import com.example.whopper.interfaces.library.dto.LibraryElementDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface LibraryMongoRepository extends MongoRepository<LibraryEntity, String> {
    Optional<LibraryEntity> findFirstByAccessRightNotAndYear(LibraryElementDto.AccessRight accessRight, int year);
    Optional<LibraryEntity> findFirstByAccessRightNot(LibraryElementDto.AccessRight accessRight);
    Optional<LibraryEntity> findFirstByYear(int year);
    Stream<LibraryEntity> findTop3ByOrderByCreateAtDesc();
    Stream<LibraryEntity> findAllByYear(int year);
}
