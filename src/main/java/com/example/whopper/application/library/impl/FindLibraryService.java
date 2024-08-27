package com.example.whopper.application.library.impl;

import com.example.whopper.application.file.usecase.PdfUseCase;
import com.example.whopper.application.library.usecase.FindLibraryUseCase;
import com.example.whopper.domain.library.LibraryModel;
import com.example.whopper.domain.library.LibraryRepository;
import com.example.whopper.interfaces.library.dto.response.LibraryDetailResponse;
import com.example.whopper.interfaces.library.dto.response.LibraryResponse;
import com.example.whopper.common.exception.library.LibraryNotFoundException;
import com.example.whopper.common.http.response.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindLibraryService implements FindLibraryUseCase {

    private final LibraryRepository libraryRepository;
    private final PdfUseCase pdfUseCase;

    @Override
    public LibraryDetailResponse findLibraryDetail(String id) {
        LibraryModel library = libraryRepository.findById(id)
                .orElseThrow(() -> LibraryNotFoundException.EXCEPTION);

        return new LibraryDetailResponse(library, pdfUseCase.getPdfFileUrl(library.filePath()));
    }

    @Override
    public DataResponseInfo<LibraryResponse> findLibrary(Integer year) {
        var library = (year == 0)
                ? libraryRepository.findAll()
                : libraryRepository.findAllByYear(year);

        return DataResponseInfo.of(library
                .map(LibraryResponse::fromEntity)
                .toList());
    }
}
