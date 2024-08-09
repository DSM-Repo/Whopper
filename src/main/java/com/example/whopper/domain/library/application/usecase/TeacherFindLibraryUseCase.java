package com.example.whopper.domain.library.application.usecase;

import com.example.whopper.domain.library.dto.response.LibraryResponse;

import java.util.List;

public interface TeacherFindLibraryUseCase {
    List<LibraryResponse> teacherFindLibrary(Integer year);
}
