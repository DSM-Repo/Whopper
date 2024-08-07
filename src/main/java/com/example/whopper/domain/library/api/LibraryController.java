package com.example.whopper.domain.library.api;

import com.example.whopper.domain.library.application.usecase.StudentFindLibraryUseCase;
import com.example.whopper.domain.library.application.usecase.TeacherFindLibraryUseCase;
import com.example.whopper.domain.library.dto.response.LibraryResponse;
import com.example.whopper.global.annotation.OnlyStudent;
import com.example.whopper.global.annotation.OnlyTeacher;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryController {

    private final StudentFindLibraryUseCase studentFindLibraryUseCase;
    private final TeacherFindLibraryUseCase teacherFindLibraryUseCase;

    @OnlyStudent
    @GetMapping("/student")
    public DataResponseInfo<LibraryResponse> studentFindLibrary(@RequestParam Integer year) {
        return studentFindLibraryUseCase.studentFindLibrary(year);
    }

    @OnlyTeacher
    @GetMapping("/teacher")
    public DataResponseInfo<LibraryResponse> teacherFindLibrary(@RequestParam Integer year) {
        return teacherFindLibraryUseCase.teacherFindLibrary(year);
    }
}
