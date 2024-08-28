package com.example.whopper.application.library.usecase;

import com.example.whopper.interfaces.library.dto.response.LibraryDetailResponse;
import com.example.whopper.interfaces.library.dto.response.LibraryResponse;
import com.example.whopper.common.http.response.DataResponseInfo;

public interface FindLibraryUseCase {
    LibraryDetailResponse findLibraryDetail(String libraryId);
    DataResponseInfo<LibraryResponse> findLibrary(Integer year);
}