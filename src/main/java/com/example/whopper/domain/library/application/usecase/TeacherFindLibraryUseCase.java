package com.example.whopper.domain.library.application.usecase;

import com.example.whopper.domain.library.dto.response.LibraryResponse;
import com.example.whopper.global.utils.DataResponseInfo;

import java.util.List;

public interface TeacherFindLibraryUseCase {
    DataResponseInfo<LibraryResponse> teacherFindLibrary(Integer year);
}
