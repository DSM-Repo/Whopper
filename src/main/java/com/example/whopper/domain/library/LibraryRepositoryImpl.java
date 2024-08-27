package com.example.whopper.domain.library;

import com.example.whopper.interfaces.library.dto.LibraryElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;
@Repository
@RequiredArgsConstructor
class LibraryRepositoryImpl implements LibraryRepository {
    private final LibraryMongoRepository libraryMongoRepository;
    private final LibraryEntityMapper libraryEntityMapper;

    public Optional<LibraryModel> findFirstByAccessRightNotAndYear(LibraryElementDto.AccessRight accessRight, int year) {
        return libraryMongoRepository.findFirstByAccessRightNotAndYear(accessRight, year)
                .map(libraryEntityMapper::toModel);
    }

    public Optional<LibraryModel> findFirstByAccessRightNot(LibraryElementDto.AccessRight accessRight) {
        return libraryMongoRepository.findFirstByAccessRightNot(accessRight)
                .map(libraryEntityMapper::toModel);
    }

    public Optional<LibraryModel> findFirstByYear(int year) {
        return libraryMongoRepository.findFirstByYear(year)
                .map(libraryEntityMapper::toModel);
    }

    public Stream<LibraryModel> findTop3ByOrderByCreateAtDesc() {
        return libraryMongoRepository.findTop3ByOrderByCreateAtDesc()
                .map(libraryEntityMapper::toModel);
    }

    public Stream<LibraryModel> findAllByYear(int year) {
        return libraryMongoRepository.findAllByYear(year)
                .map(libraryEntityMapper::toModel);
    }
}
