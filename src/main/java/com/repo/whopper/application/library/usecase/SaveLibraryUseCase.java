package com.repo.whopper.application.library.usecase;

import com.repo.whopper.common.http.dto.DataResponseInfo;
import com.repo.whopper.interfaces.library.dto.LibraryElementDto;

public interface SaveLibraryUseCase {
    void saveLibrary(Integer grade, String filePath, DataResponseInfo<LibraryElementDto.ResumeIndex> index);
}