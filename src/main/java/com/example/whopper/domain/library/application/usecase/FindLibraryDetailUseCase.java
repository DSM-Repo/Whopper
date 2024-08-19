package com.example.whopper.domain.library.application.usecase;

import com.example.whopper.domain.library.dto.response.LibraryDetailResponse;

public interface FindLibraryDetailUseCase {
    LibraryDetailResponse findLibraryDetail(String libraryId);
}
