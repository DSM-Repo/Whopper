package com.dsm.repo.internal.data.repository.library;

import com.dsm.repo.internal.core.domain.model.library.LibraryModel;

import java.util.Optional;
import java.util.stream.Stream;

public interface LibraryRepository {
    LibraryModel save(LibraryModel model);
    Optional<LibraryModel> findById(String libraryId);
    Optional<LibraryModel> findPublicById(String libraryId);
    Stream<LibraryModel> findAll();
    Stream<LibraryModel> findTop3ByOrderByCreateAtDesc();
    Stream<LibraryModel> findAllByYear(int year);
}