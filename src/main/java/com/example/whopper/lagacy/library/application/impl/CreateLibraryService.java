package com.example.whopper.lagacy.library.application.impl;

import com.example.whopper.lagacy.library.application.usecase.CreateLibraryUseCase;
import com.example.whopper.lagacy.library.dao.LibraryMongoRepository;
import com.example.whopper.lagacy.library.domain.DocumentIndex;
import com.example.whopper.lagacy.library.domain.LibraryEntity;
import com.example.whopper.lagacy.library.domain.type.AccessRight;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateLibraryService implements CreateLibraryUseCase {

    private final LibraryMongoRepository libraryMongoRepository;

    public void createLibrary(Integer grade, String filePath, DataResponseInfo<DocumentIndex> index) {
        LocalDateTime now = LocalDateTime.now();

        libraryMongoRepository.save(
                LibraryEntity.builder()
                        .year(now.getYear())
                        .grade(grade)
                        .filePath(filePath)
                        .createAt(now)
                        .accessRight(AccessRight.PRIVATE)
                        .index(index.data())
                        .build());
    }
}
