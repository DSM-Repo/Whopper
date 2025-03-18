package com.dsm.repo.internal.core.usecase.library;

import com.dsm.repo.external.web.rest.common.DataResponseInfo;
import com.dsm.repo.external.web.rest.library.dto.LibraryElementDto;

public interface SaveLibraryUseCase {
    void saveLibrary(Integer grade, String filePath, DataResponseInfo<LibraryElementDto.ResumeIndex> index);
}