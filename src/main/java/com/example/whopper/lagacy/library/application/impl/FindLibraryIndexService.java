package com.example.whopper.lagacy.library.application.impl;

import com.example.whopper.lagacy.library.application.usecase.FindLibraryIndexUseCase;
import com.example.whopper.lagacy.library.dao.LibraryMongoRepository;
import com.example.whopper.lagacy.library.domain.LibraryEntity;
import com.example.whopper.lagacy.library.dto.response.LibraryIndexResponse;
import com.example.whopper.lagacy.library.exception.LibraryNotFoundException;
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
