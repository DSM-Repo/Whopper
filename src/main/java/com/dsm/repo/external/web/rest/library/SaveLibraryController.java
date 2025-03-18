package com.dsm.repo.external.web.rest.library;

import com.dsm.repo.internal.core.usecase.file.PdfUseCase;
import com.dsm.repo.internal.core.usecase.library.SaveLibraryUseCase;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;
import com.dsm.repo.external.web.documentation.library.SaveLibraryApiDocumentation;
import com.dsm.repo.external.web.rest.library.dto.request.ResumeIndexRequest;
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
class SaveLibraryController implements SaveLibraryApiDocumentation {

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
