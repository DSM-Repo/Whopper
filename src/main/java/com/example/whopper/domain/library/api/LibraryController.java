package com.example.whopper.domain.library.api;

import com.example.whopper.domain.library.application.usecase.ChangeLibraryAccessRightUseCase;
import com.example.whopper.domain.library.application.usecase.FindLibraryDetailUseCase;
import com.example.whopper.domain.library.application.usecase.FindLibraryIndexUseCase;
import com.example.whopper.domain.library.application.usecase.StudentFindLibraryUseCase;
import com.example.whopper.domain.library.application.usecase.TeacherFindLibraryUseCase;
import com.example.whopper.domain.library.domain.type.AccessRight;
import com.example.whopper.domain.library.dto.LibraryDetailResponse;
import com.example.whopper.domain.library.dto.LibraryIndexResponse;
import com.example.whopper.domain.library.dto.LibraryResponse;
import com.example.whopper.global.annotation.OnlyStudent;
import com.example.whopper.global.annotation.OnlyTeacher;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryController {

    private final StudentFindLibraryUseCase studentFindLibraryUseCase;
    private final TeacherFindLibraryUseCase teacherFindLibraryUseCase;
    private final FindLibraryIndexUseCase findLibraryIndexUseCase;
    private final ChangeLibraryAccessRightUseCase changeLibraryAccessRightUseCase;
    private final FindLibraryDetailUseCase findLibraryDetailUseCase;

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

    @GetMapping("/{libraryId}/index")
    public LibraryIndexResponse findLibrary(@PathVariable String libraryId) {
        return findLibraryIndexUseCase.findLibraryIndex(libraryId);
    }

    @OnlyTeacher
    @PatchMapping("/{libraryId}/access-right")
    public void changeLibraryAccessRight(@PathVariable String libraryId, @RequestParam AccessRight accessRight) {
        changeLibraryAccessRightUseCase.changeLibraryAccessRight(libraryId, accessRight);
    }

    @GetMapping("/{libraryId}/public")
    public LibraryDetailResponse findLibraryDetail(@PathVariable String libraryId) {
        return findLibraryDetailUseCase.findLibraryDetail(libraryId);
    }
}
