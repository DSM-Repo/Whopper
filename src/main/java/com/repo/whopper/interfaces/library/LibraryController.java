package com.repo.whopper.interfaces.library;

import com.repo.whopper.application.file.usecase.PdfUseCase;
import com.repo.whopper.application.library.usecase.ChangeLibraryAccessRightUseCase;
import com.repo.whopper.application.library.usecase.CreateLibraryUseCase;
import com.repo.whopper.application.library.usecase.FindLibraryUseCase;
import com.repo.whopper.interfaces.library.dto.LibraryElementDto;
import com.repo.whopper.interfaces.library.dto.request.ResumeIndexRequest;
import com.repo.whopper.interfaces.library.dto.response.LibraryDetailResponse;
import com.repo.whopper.interfaces.library.dto.response.LibraryResponse;
import com.repo.whopper.common.annotation.OnlyTeacher;
import com.repo.whopper.common.http.dto.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
class LibraryController {
    private final CreateLibraryUseCase createLibraryUseCase;
    private final PdfUseCase pdfUseCase;
    private final ChangeLibraryAccessRightUseCase changeLibraryAccessRightUseCase;
    private final FindLibraryUseCase findLibraryUseCase;

    @PostMapping
    public void saveLibraryResume(
            @RequestParam(name = "grade") Integer grade,
            @RequestPart(name = "pdf") MultipartFile pdfPart,
            @RequestPart(name = "index") ResumeIndexRequest indexPart
    ) {
        String filePath = pdfUseCase.savePdf(pdfPart);
        createLibraryUseCase.createLibrary(grade, filePath, DataResponseInfo.of(indexPart.index()));
    }

    @GetMapping("/{libraryId}")
    LibraryDetailResponse getLibraryDetailResponse(@PathVariable String libraryId) {
        return findLibraryUseCase.findLibraryDetail(libraryId);
    }

    @GetMapping
    DataResponseInfo<LibraryResponse> findLibrary(@RequestParam(defaultValue = "0") Integer year) {
        return findLibraryUseCase.findLibrary(year);
    }

    @OnlyTeacher
    @PatchMapping("/{libraryId}")
    void changeLibraryAccessRight(
            @PathVariable String libraryId,
            @RequestParam(name = "access-right") LibraryElementDto.AccessRight accessRight
    ) {
        changeLibraryAccessRightUseCase.changeLibraryAccessRight(libraryId, accessRight);
    }

    @GetMapping("/{libraryId}/public")
    LibraryDetailResponse findLibraryDetail(@PathVariable String libraryId) {
        return findLibraryUseCase.findLibraryDetail(libraryId);
    }

}
