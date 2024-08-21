package com.example.whopper.interfaces.library;

import com.example.whopper.application.file.usecase.PdfUseCase;
import com.example.whopper.application.library.usecase.ChangeLibraryAccessRightUseCase;
import com.example.whopper.application.library.usecase.CreateLibraryUseCase;
import com.example.whopper.application.library.usecase.FindLibraryUseCase;
import com.example.whopper.domain.library.type.AccessRight;
import com.example.whopper.interfaces.library.dto.request.DocumentIndexRequest;
import com.example.whopper.interfaces.library.dto.response.LibraryDetailResponse;
import com.example.whopper.interfaces.library.dto.response.LibraryResponse;
import com.example.whopper.common.annotation.OnlyTeacher;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
    public void saveLibraryDocument(
            @RequestParam(name = "grade") Integer grade,
            @RequestPart("pdf") MultipartFile pdfPart,
            @RequestPart("index") DocumentIndexRequest indexPart) {


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
    @PatchMapping("/{libraryId}/access-right")
    public void changeLibraryAccessRight(@PathVariable String libraryId, @RequestParam AccessRight accessRight) {
        changeLibraryAccessRightUseCase.changeLibraryAccessRight(libraryId, accessRight);
    }

    @GetMapping("/{libraryId}/public")
    public LibraryDetailResponse findLibraryDetail(@PathVariable String libraryId) {
        return findLibraryUseCase.findLibraryDetail(libraryId);
    }
}
