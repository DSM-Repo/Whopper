package com.example.whopper.domain.library.application.usecase;

import com.example.whopper.domain.library.dto.LibraryIndexResponse;

public interface FindLibraryIndexUseCase {
    LibraryIndexResponse findLibraryIndex(String libraryId);
}
