package com.repo.whopper.application.library.usecase;

import com.repo.whopper.common.http.dto.DataResponseInfo;
import com.repo.whopper.interfaces.library.dto.LibraryElementDto;

public interface CreateLibraryUseCase {
    void createLibrary(Integer grade, String filePath, DataResponseInfo<LibraryElementDto.ResumeIndex> index);
}