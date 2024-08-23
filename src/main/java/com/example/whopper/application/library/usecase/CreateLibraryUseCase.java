package com.example.whopper.application.library.usecase;

import com.example.whopper.domain.library.DocumentIndex;
import com.example.whopper.common.http.response.DataResponseInfo;

public interface CreateLibraryUseCase {
    void createLibrary(Integer grade, String filePath, DataResponseInfo<DocumentIndex> index);
}
