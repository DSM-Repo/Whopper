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
        return libraryEntityMapper.toOptionalModel(libraryMongoRepository.findById(id));
    }

    @Override
    public Optional<LibraryModel> findFirstByAccessRightNotAndYear(LibraryElementDto.AccessRight accessRight, int year) {
        return libraryEntityMapper.toOptionalModel(libraryMongoRepository.findFirstByAccessRightNotAndYear(LibraryEntity.AccessRight.valueOf(accessRight.name()), year));
    }

    @Override
    public Optional<LibraryModel> findFirstByAccessRightNot(LibraryElementDto.AccessRight accessRight) {
        return libraryEntityMapper.toOptionalModel(libraryMongoRepository.findFirstByAccessRightNot(LibraryEntity.AccessRight.valueOf(accessRight.name())));
    }

    @Override
    public Optional<LibraryModel> findFirstByYear(int year) {
        return libraryEntityMapper.toOptionalModel(libraryMongoRepository.findFirstByYear(year));
    }

    @Override
    public Stream<LibraryModel> findAll() {
        final var result = libraryMongoRepository.findAll().stream();

        return toStreamLibraryModel(result);
    }

    @Override
    public Stream<LibraryModel> findTop3ByOrderByCreateAtDesc() {
        final var result = libraryMongoRepository.findTop3ByOrderByCreateAtDesc();

        return toStreamLibraryModel(result);
    }

    @Override
    public Stream<LibraryModel> findAllByYear(int year) {
        final var result = libraryMongoRepository.findAllByYear(year);

        return toStreamLibraryModel(result);
    }

    private Stream<LibraryModel> toStreamLibraryModel(Stream<LibraryEntity> entityStream) {
        return entityStream.map(libraryEntityMapper::toModel);
    }
}
