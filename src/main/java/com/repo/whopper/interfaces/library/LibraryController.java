package com.repo.whopper.interfaces.library;

import com.repo.whopper.application.library.usecase.ChangeLibraryAccessRightUseCase;
import com.repo.whopper.application.library.usecase.FindLibraryUseCase;
import com.repo.whopper.interfaces.library.dto.LibraryElementDto;
import com.repo.whopper.interfaces.library.dto.response.LibraryDetailResponse;
import com.repo.whopper.interfaces.library.dto.response.LibraryResponse;
import com.repo.whopper.common.annotation.OnlyTeacher;
import com.repo.whopper.common.http.dto.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryController {
    private final ChangeLibraryAccessRightUseCase changeLibraryAccessRightUseCase;
    private final FindLibraryUseCase findLibraryUseCase;

    @GetMapping("/{libraryId}")
    public LibraryDetailResponse getLibraryDetailResponse(@PathVariable String libraryId) {
        return findLibraryUseCase.findLibraryDetail(libraryId);
    }

    @GetMapping
    public DataResponseInfo<LibraryResponse> studentFindLibrary(@RequestParam(defaultValue = "0") Integer year) {
        return findLibraryUseCase.findLibrary(year);
    }

    @OnlyTeacher
    @PatchMapping("/access-right")
    public void changeLibraryAccessRight(@RequestBody AccessRightRequest request) {
        changeLibraryAccessRightUseCase.changeLibraryAccessRight(request.libraryId(), request.accessRight());
    }

    @GetMapping("/{libraryId}/public")
    public LibraryDetailResponse findLibraryDetail(@PathVariable String libraryId) {
        return findLibraryUseCase.findLibraryDetail(libraryId);
    }

    record AccessRightRequest(String libraryId, LibraryElementDto.AccessRight accessRight) {}
}
