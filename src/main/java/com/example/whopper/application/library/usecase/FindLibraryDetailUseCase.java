package com.example.whopper.application.library.usecase;

import com.example.whopper.interfaces.library.dto.response.LibraryDetailResponse;

public interface FindLibraryDetailUseCase {
    LibraryDetailResponse findLibraryDetail(String libraryId);
}
