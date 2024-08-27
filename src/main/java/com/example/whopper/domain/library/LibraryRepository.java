package com.example.whopper.domain.library;

import com.example.whopper.interfaces.library.dto.LibraryElementDto;

import java.util.Optional;
import java.util.stream.Stream;

public interface LibraryRepository {
    LibraryModel save(LibraryModel library);
    Optional<LibraryModel> findById(String id);
    Optional<LibraryModel> findFirstByAccessRightNotAndYear(LibraryElementDto.AccessRight accessRight, int year);
    Optional<LibraryModel> findFirstByAccessRightNot(LibraryElementDto.AccessRight accessRight);
    Optional<LibraryModel> findFirstByYear(int year);
    Stream<LibraryModel> findAll();
    Stream<LibraryModel> findTop3ByOrderByCreateAtDesc();
    Stream<LibraryModel> findAllByYear(int year);
}
