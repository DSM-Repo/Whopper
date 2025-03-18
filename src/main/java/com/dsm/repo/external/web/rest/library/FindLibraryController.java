package com.dsm.repo.external.web.rest.library;

import com.dsm.repo.internal.core.usecase.library.FindLibraryUseCase;
import com.dsm.repo.external.web.documentation.library.FindLibraryApiDocumentation;
import com.dsm.repo.external.web.rest.library.dto.response.LibraryDetailResponse;
import com.dsm.repo.external.web.rest.library.dto.response.LibraryResponse;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
class FindLibraryController implements FindLibraryApiDocumentation {

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
