package com.example.whopper.domain.library;

import com.example.whopper.interfaces.library.dto.LibraryElementDto;

import java.util.Optional;
import java.util.stream.Stream;

public interface LibraryRepository {
    Optional<LibraryModel> findFirstByAccessRightNotAndYear(LibraryElementDto.AccessRight accessRight, int year);
    Optional<LibraryModel> findFirstByAccessRightNot(LibraryElementDto.AccessRight accessRight);
    Optional<LibraryModel> findFirstByYear(int year);
    Stream<LibraryModel> findTop3ByOrderByCreateAtDesc();
    Stream<LibraryModel> findAllByYear(int year);
}
