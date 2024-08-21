package com.example.whopper.lagacy.library.application.usecase;

import com.example.whopper.lagacy.library.dto.response.LibraryDetailResponse;
import com.example.whopper.lagacy.library.dto.response.LibraryResponse;
import com.example.whopper.global.utils.DataResponseInfo;

public interface FindLibraryUseCase {
    LibraryDetailResponse findLibraryDetail(String libraryId);
    DataResponseInfo<LibraryResponse> findLibrary(Integer year);
}
