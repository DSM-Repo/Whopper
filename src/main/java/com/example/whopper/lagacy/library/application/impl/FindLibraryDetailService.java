package com.example.whopper.lagacy.library.application.impl;

import com.example.whopper.lagacy.file.application.usecase.PdfUseCase;
import com.example.whopper.lagacy.library.application.usecase.FindLibraryDetailUseCase;
import com.example.whopper.lagacy.library.dao.LibraryMongoRepository;
import com.example.whopper.lagacy.library.domain.LibraryEntity;
import com.example.whopper.lagacy.library.dto.response.LibraryDetailResponse;
import com.example.whopper.lagacy.library.exception.LibraryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindLibraryDetailService implements FindLibraryDetailUseCase {

    private final LibraryMongoRepository libraryMongoRepository;
    private final PdfUseCase pdfUseCase;

    public LibraryDetailResponse findLibraryDetail(String libraryId) {
        LibraryEntity library =  libraryMongoRepository.findById(libraryId)
                .orElseThrow(() -> LibraryNotFoundException.EXCEPTION);

        return new LibraryDetailResponse(library, pdfUseCase.getPdfFileUrl(library.getFilePath()));
    }
}
