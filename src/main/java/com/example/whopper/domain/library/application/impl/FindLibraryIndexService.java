package com.example.whopper.domain.library.application.impl;

import com.example.whopper.domain.library.application.usecase.FindLibraryIndexUseCase;
import com.example.whopper.domain.library.dao.LibraryMongoRepository;
import com.example.whopper.domain.library.domain.LibraryEntity;
import com.example.whopper.domain.library.dto.response.LibraryIndexResponse;
import com.example.whopper.domain.library.exception.LibraryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindLibraryIndexService implements FindLibraryIndexUseCase {

    private final LibraryMongoRepository libraryMongoRepository;

    public LibraryIndexResponse findLibraryIndex(String libraryId) {
        LibraryEntity library = libraryMongoRepository.findById(libraryId)
                .orElseThrow(() -> LibraryNotFoundException.EXCEPTION);

        return new LibraryIndexResponse(library.getIndex());
    }
}
