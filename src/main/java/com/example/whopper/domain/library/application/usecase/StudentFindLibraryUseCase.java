package com.example.whopper.domain.library.application.usecase;

import com.example.whopper.domain.library.dto.response.LibraryResponse;

import java.util.List;

public interface StudentFindLibraryUseCase {
    List<LibraryResponse> studentFindLibrary(Integer year);
}