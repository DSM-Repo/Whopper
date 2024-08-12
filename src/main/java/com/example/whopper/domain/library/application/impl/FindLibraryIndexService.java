package com.example.whopper.domain.library.application.impl;

import com.example.whopper.domain.library.application.component.FindLibraryByIdComponent;
import com.example.whopper.domain.library.application.usecase.FindLibraryIndexUseCase;
import com.example.whopper.domain.library.domain.LibraryEntity;
import com.example.whopper.domain.library.dto.LibraryIndexResponse;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindLibraryIndexService implements FindLibraryIndexUseCase {

    private final FindLibraryByIdComponent findLibraryByIdComponent;

    public LibraryIndexResponse findLibraryIndex(String libraryId) {
        LibraryEntity library = findLibraryByIdComponent.findLibraryById(libraryId);

        return new LibraryIndexResponse(DataResponseInfo.of(library.getIndex()));
    }
}
