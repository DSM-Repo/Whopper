package com.dsm.repo.internal.core.usecase.library;

import com.dsm.repo.external.web.rest.library.dto.response.LibraryDetailResponse;
import com.dsm.repo.external.web.rest.library.dto.response.LibraryResponse;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;

public interface FindLibraryUseCase {
    LibraryDetailResponse findLibraryDetail(String libraryId);
    LibraryDetailResponse findPublicLibrary(String libraryId);
    DataResponseInfo<LibraryResponse> findLibrary(Integer year);
}