package com.example.whopper.domain.library.application.usecase;

import com.example.whopper.domain.library.domain.DocumentIndex;
import com.example.whopper.global.utils.DataResponseInfo;

public interface CreateLibraryUseCase {
    void createLibrary(Integer grade, String filePath, DataResponseInfo<DocumentIndex> index);
}
