package com.example.whopper.domain.library;

import java.util.Optional;
import java.util.stream.Stream;

public interface LibraryRepository {
    LibraryModel save(LibraryModel model);
    Optional<LibraryModel> findById(String libraryId);
    Stream<LibraryModel> findAll();
    Stream<LibraryModel> findTop3ByOrderByCreateAtDesc();
    Stream<LibraryModel> findAllByYear(int year);
}