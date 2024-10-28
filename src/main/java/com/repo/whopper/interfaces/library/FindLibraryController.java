package com.repo.whopper.interfaces.library;

import com.repo.whopper.application.library.usecase.FindLibraryUseCase;
import com.repo.whopper.common.swagger.library.FindLibraryApiDocumentation;
import com.repo.whopper.interfaces.library.dto.response.LibraryDetailResponse;
import com.repo.whopper.interfaces.library.dto.response.LibraryResponse;
import com.repo.whopper.common.http.dto.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class FindLibraryController implements FindLibraryApiDocumentation {
    private final FindLibraryUseCase findLibraryUseCase;

    @GetMapping("/{libraryId}")
    public LibraryDetailResponse getLibraryDetailResponse(@PathVariable String libraryId) {
        return findLibraryUseCase.findLibraryDetail(libraryId);
    }

    @GetMapping
    public DataResponseInfo<LibraryResponse> findLibrary(@RequestParam(defaultValue = "0") Integer year) {
        return findLibraryUseCase.findLibrary(year);
    }

    @GetMapping("/{libraryId}/public")
    public LibraryDetailResponse findLibraryDetail(@PathVariable String libraryId) {
        return findLibraryUseCase.findPublicLibrary(libraryId);
    }
}
