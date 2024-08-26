package com.example.whopper.application.library.impl;

import com.example.whopper.application.file.usecase.PdfUseCase;
import com.example.whopper.application.library.usecase.FindLibraryUseCase;
import com.example.whopper.domain.library.LibraryMongoRepository;
import com.example.whopper.domain.library.LibraryEntity;
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

    private final LibraryMongoRepository libraryMongoRepository;
    private final PdfUseCase pdfUseCase;

    @Override
    public LibraryDetailResponse findLibraryDetail(String libraryId) {
        LibraryEntity library =  libraryMongoRepository.findById(libraryId)
                .orElseThrow(() -> LibraryNotFoundException.EXCEPTION);

        return new LibraryDetailResponse(library, pdfUseCase.getPdfFileUrl(library.getFilePath()));
    }

    @Override
    public DataResponseInfo<LibraryResponse> findLibrary(Integer year) {
        var library = (year == 0)
                ? libraryMongoRepository.findAll()
                : libraryMongoRepository.findAllByYear(year);

        return DataResponseInfo.of(library.stream()
                .map(LibraryResponse::fromEntity)
                .toList());
    }
}
