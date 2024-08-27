package com.example.whopper.application.library.impl;

import com.example.whopper.application.file.usecase.PdfUseCase;
import com.example.whopper.application.library.usecase.FindLibraryDetailUseCase;
import com.example.whopper.domain.library.LibraryModel;
import com.example.whopper.domain.library.LibraryMongoRepository;
import com.example.whopper.domain.library.LibraryEntity;
import com.example.whopper.domain.library.LibraryRepository;
import com.example.whopper.interfaces.library.dto.response.LibraryDetailResponse;
import com.example.whopper.common.exception.library.LibraryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindLibraryDetailService implements FindLibraryDetailUseCase {

    private final LibraryRepository libraryRepository;
    private final PdfUseCase pdfUseCase;

    @Override
    @Transactional(readOnly = true)
    public LibraryDetailResponse findLibraryDetail(String id) {
        LibraryModel library = libraryRepository.findById(id)
                .orElseThrow(() -> LibraryNotFoundException.EXCEPTION);

        return new LibraryDetailResponse(library, pdfUseCase.getPdfFileUrl(library.filePath()));
    }
}
