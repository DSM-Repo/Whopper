package com.dsm.repo.internal.data.repository.library;

import com.dsm.repo.internal.core.domain.model.library.LibraryModel;
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
    public LibraryModel save(LibraryModel model) {
        final var entity =  libraryEntityMapper.toEntity(model);

        return libraryEntityMapper.toModel(libraryMongoRepository.save(entity));
    }

    @Override
    public Optional<LibraryModel> findById(String libraryId) {
        return libraryEntityMapper.toOptionalModel(libraryMongoRepository.findById(libraryId));
    }

    @Override
    public Optional<LibraryModel> findPublicById(final String libraryId) {
        final var entity = libraryMongoRepository.findByIdAndAccessRight(libraryId, LibraryEntity.AccessRight.PUBLIC);
        return libraryEntityMapper.toOptionalModel(entity);
    }

    @Override
    public Stream<LibraryModel> findAll() {
        final var result = libraryMongoRepository.findAll().stream();

        return libraryEntityMapper.toStreamLibraryModel(result);
    }

    @Override
    public Stream<LibraryModel> findTop3ByOrderByCreateAtDesc() {
        final var result = libraryMongoRepository.findTop3ByOrderByCreateAtDesc();

        return libraryEntityMapper.toStreamLibraryModel(result);
    }

    @Override
    public Stream<LibraryModel> findAllByYear(int year) {
        final var result = libraryMongoRepository.findAllByYear(year);

        return libraryEntityMapper.toStreamLibraryModel(result);
    }
}