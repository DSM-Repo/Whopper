package com.example.whopper.application.library.usecase;

import com.example.whopper.common.http.response.DataResponseInfo;
import com.example.whopper.interfaces.library.dto.LibraryElementDto;

public interface CreateLibraryUseCase {
    void createLibrary(Integer grade, String filePath, DataResponseInfo<LibraryElementDto.ResumeIndex> index);
}
