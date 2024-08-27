package com.example.whopper.interfaces.library;

import com.example.whopper.application.file.usecase.PdfUseCase;
import com.example.whopper.application.library.usecase.ChangeLibraryAccessRightUseCase;
import com.example.whopper.application.library.usecase.CreateLibraryUseCase;
import com.example.whopper.application.library.usecase.FindLibraryUseCase;
import com.example.whopper.interfaces.library.dto.LibraryElementDto;
import com.example.whopper.interfaces.library.dto.request.ResumeIndexRequest;
import com.example.whopper.interfaces.library.dto.response.LibraryDetailResponse;
import com.example.whopper.interfaces.library.dto.response.LibraryResponse;
import com.example.whopper.common.annotation.OnlyTeacher;
import com.example.whopper.common.http.response.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryController {

    private final CreateLibraryUseCase createLibraryUseCase;
    private final PdfUseCase pdfUseCase;
    private final ChangeLibraryAccessRightUseCase changeLibraryAccessRightUseCase;
    private final FindLibraryUseCase findLibraryUseCase;

    @PostMapping
    public void saveLibraryResume(
            @RequestParam(name = "grade") Integer grade,
            @RequestPart(name = "pdf") MultipartFile pdfPart,
            @RequestPart(name = "index") ResumeIndexRequest indexPart) {


        String filePath = pdfUseCase.savePdf(pdfPart);
        createLibraryUseCase.createLibrary(grade, filePath, DataResponseInfo.of(indexPart.index()));
    }

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
