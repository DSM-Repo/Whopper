package com.example.whopper.domain.library.application.usecase;

import com.example.whopper.domain.library.dto.LibraryDetailResponse;
import com.example.whopper.domain.library.dto.LibraryResponse;
import com.example.whopper.global.utils.DataResponseInfo;

public interface FindLibraryUseCase {
    LibraryDetailResponse findLibraryDetail(String libraryId);
    DataResponseInfo<LibraryResponse> findLibrary(Integer year);
}
