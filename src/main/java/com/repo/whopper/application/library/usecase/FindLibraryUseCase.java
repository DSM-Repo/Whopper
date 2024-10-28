package com.repo.whopper.application.library.usecase;

import com.repo.whopper.interfaces.library.dto.response.LibraryDetailResponse;
import com.repo.whopper.interfaces.library.dto.response.LibraryResponse;
import com.repo.whopper.common.http.dto.DataResponseInfo;

public interface FindLibraryUseCase {
    LibraryDetailResponse findLibraryDetail(String libraryId);
    LibraryDetailResponse findPublicLibrary(String libraryId);
    DataResponseInfo<LibraryResponse> findLibrary(Integer year);
}