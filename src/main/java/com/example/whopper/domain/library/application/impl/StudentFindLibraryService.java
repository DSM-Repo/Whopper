package com.example.whopper.domain.library.application.impl;

import com.example.whopper.domain.library.application.dao.LibraryAdapter;
import com.example.whopper.domain.library.application.usecase.StudentFindLibraryUseCase;
import com.example.whopper.domain.library.domain.type.AccessRight;
import com.example.whopper.domain.library.dto.response.LibraryResponse;
import com.example.whopper.global.utils.DataResponseInfo;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentFindLibraryService implements StudentFindLibraryUseCase {

    private final LibraryAdapter libraryAdapter;

    public DataResponseInfo<LibraryResponse> studentFindLibrary(@Nullable Integer year) {
        var arthurLibrary = libraryAdapter.getLibrary(AccessRight.PRIVATE, year);

        return DataResponseInfo.of(arthurLibrary.stream()
                .map(LibraryResponse::from).toList());
    }
}
