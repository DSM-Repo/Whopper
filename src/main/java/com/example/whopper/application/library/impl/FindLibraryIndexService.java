package com.example.whopper.application.library.impl;

import com.example.whopper.application.library.usecase.FindLibraryIndexUseCase;
import com.example.whopper.domain.library.LibraryMongoRepository;
import com.example.whopper.domain.library.LibraryEntity;
import com.example.whopper.interfaces.library.dto.response.LibraryIndexResponse;
import com.example.whopper.common.exception.library.LibraryNotFoundException;
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
