package com.example.whopper.application.library.impl;

import com.example.whopper.application.library.usecase.FindLibraryIndexUseCase;
import com.example.whopper.domain.library.LibraryModel;
import com.example.whopper.domain.library.LibraryRepository;
import com.example.whopper.interfaces.library.dto.response.LibraryIndexResponse;
import com.example.whopper.common.exception.library.LibraryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindLibraryIndexService implements FindLibraryIndexUseCase {

    private final LibraryRepository libraryRepository;

    @Override
    @Transactional(readOnly = true)
    public LibraryIndexResponse findLibraryIndex(String id) {
        LibraryModel library = libraryRepository.findById(id)
                .orElseThrow(() -> LibraryNotFoundException.EXCEPTION);

        return new LibraryIndexResponse(library.index());
    }
}
