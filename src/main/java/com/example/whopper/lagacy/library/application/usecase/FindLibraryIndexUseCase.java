package com.example.whopper.lagacy.library.application.usecase;

import com.example.whopper.lagacy.library.dto.response.LibraryIndexResponse;

public interface FindLibraryIndexUseCase {
    LibraryIndexResponse findLibraryIndex(String libraryId);
}
