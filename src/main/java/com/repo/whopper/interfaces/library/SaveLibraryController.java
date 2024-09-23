package com.repo.whopper.interfaces.library;

import com.repo.whopper.application.file.usecase.PdfUseCase;
import com.repo.whopper.application.library.usecase.SaveLibraryUseCase;
import com.repo.whopper.common.http.dto.DataResponseInfo;
import com.repo.whopper.common.swagger.library.SaveLibraryApiDocumentation;
import com.repo.whopper.interfaces.library.dto.request.ResumeIndexRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class SaveLibraryController implements SaveLibraryApiDocumentation {
    private final SaveLibraryUseCase saveLibraryUseCase;
    private final PdfUseCase pdfUseCase;

    @PostMapping
    public void saveLibrary(
            @RequestParam(name = "grade") Integer grade,
            @RequestPart(name = "pdf") MultipartFile pdfPart,
            @RequestPart(name = "index") ResumeIndexRequest indexPart
    ) {
        String filePath = pdfUseCase.savePdf(pdfPart);
        saveLibraryUseCase.saveLibrary(grade, filePath, DataResponseInfo.of(indexPart.index()));
    }
}
