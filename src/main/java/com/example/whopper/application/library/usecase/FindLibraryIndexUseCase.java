package com.example.whopper.application.library.usecase;

import com.example.whopper.interfaces.library.dto.response.LibraryIndexResponse;

public interface FindLibraryIndexUseCase {
    LibraryIndexResponse findLibraryIndex(String libraryId);
}
