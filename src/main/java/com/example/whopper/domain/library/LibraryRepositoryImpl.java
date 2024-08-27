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

    @Override
    public LibraryModel save(LibraryModel library) {
        final var entity =  libraryEntityMapper.toEntity(library);

        return libraryEntityMapper.toModel(libraryMongoRepository.save(entity));
    }

    @Override
    public Optional<LibraryModel> findById(String id) {
        return libraryMongoRepository.findById(id)
                .map(libraryEntityMapper::toModel);
    }

    @Override
    public Optional<LibraryModel> findFirstByAccessRightNotAndYear(LibraryElementDto.AccessRight accessRight, int year) {
        return libraryMongoRepository.findFirstByAccessRightNotAndYear(accessRight, year)
                .map(libraryEntityMapper::toModel);
    }

    @Override
    public Optional<LibraryModel> findFirstByAccessRightNot(LibraryElementDto.AccessRight accessRight) {
        return libraryMongoRepository.findFirstByAccessRightNot(accessRight)
                .map(libraryEntityMapper::toModel);
    }

    @Override
    public Optional<LibraryModel> findFirstByYear(int year) {
        return libraryMongoRepository.findFirstByYear(year)
                .map(libraryEntityMapper::toModel);
    }

    @Override
    public Stream<LibraryModel> findAll() {
        return libraryMongoRepository.findAll().stream()
                .map(libraryEntityMapper::toModel);
    }

    @Override
    public Stream<LibraryModel> findTop3ByOrderByCreateAtDesc() {
        return libraryMongoRepository.findTop3ByOrderByCreateAtDesc()
                .map(libraryEntityMapper::toModel);
    }

    @Override
    public Stream<LibraryModel> findAllByYear(int year) {
        return libraryMongoRepository.findAllByYear(year)
                .map(libraryEntityMapper::toModel);
    }
}
