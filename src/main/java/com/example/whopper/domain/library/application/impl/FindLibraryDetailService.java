package com.example.whopper.domain.library.application.impl;

import com.example.whopper.domain.file.application.usecase.PdfUseCase;
import com.example.whopper.domain.library.application.component.FindLibraryByIdComponent;
import com.example.whopper.domain.library.application.usecase.FindLibraryDetailUseCase;
import com.example.whopper.domain.library.domain.LibraryEntity;
import com.example.whopper.domain.library.dto.response.LibraryDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindLibraryDetailService implements FindLibraryDetailUseCase {

    private final FindLibraryByIdComponent findLibraryByIdComponent;
    private final PdfUseCase pdfUseCase;

    public LibraryDetailResponse findLibraryDetail(String libraryId) {
        LibraryEntity library =  findLibraryByIdComponent.findLibraryById(libraryId);

        return new LibraryDetailResponse(library, pdfUseCase.getPdfFileUrl(library.getFilePath()));
    }
}
