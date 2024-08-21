package com.example.whopper.lagacy.library.application.usecase;

import com.example.whopper.lagacy.library.dto.response.LibraryDetailResponse;

public interface FindLibraryDetailUseCase {
    LibraryDetailResponse findLibraryDetail(String libraryId);
}
