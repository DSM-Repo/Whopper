package com.repo.whopper.application.library.impl;

import com.repo.whopper.application.file.usecase.PdfUseCase;
import com.repo.whopper.application.library.usecase.FindLibraryUseCase;
import com.repo.whopper.domain.library.LibraryModel;
import com.repo.whopper.domain.library.LibraryRepository;
import com.repo.whopper.interfaces.library.dto.response.LibraryDetailResponse;
import com.repo.whopper.interfaces.library.dto.response.LibraryResponse;
import com.repo.whopper.common.exception.library.LibraryNotFoundException;
import com.repo.whopper.common.http.response.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindLibraryService implements FindLibraryUseCase {
    private final LibraryRepository libraryRepository;
    private final PdfUseCase pdfUseCase;

    @Override
    @Transactional(readOnly = true)
    public LibraryDetailResponse findLibraryDetail(String libraryId) {
        LibraryModel library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> LibraryNotFoundException.EXCEPTION);

        return new LibraryDetailResponse(library, pdfUseCase.getPdfFileUrl(library.filePath()));
    }

    @Override
    @Transactional(readOnly = true)
    public DataResponseInfo<LibraryResponse> findLibrary(Integer year) {
        var library = (year == 0)
                ? libraryRepository.findAll()
                : libraryRepository.findAllByYear(year);

        return DataResponseInfo.of(library
                .map(LibraryResponse::fromModel)
                .toList());
    }
}