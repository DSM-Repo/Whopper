package com.example.whopper.domain.library.api;

import com.example.whopper.domain.library.application.usecase.StudentFindLibraryUseCase;
import com.example.whopper.domain.library.dto.response.LibraryResponse;
import com.example.whopper.global.annotation.OnlyStudent;
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

    @OnlyStudent
    @GetMapping("/student")
    public List<LibraryResponse> studentFindLibrary(@RequestParam Integer year) {
        return studentFindLibraryUseCase.studentFindLibrary(year);
    }
}
