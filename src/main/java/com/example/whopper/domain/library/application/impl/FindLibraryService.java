package com.example.whopper.domain.library.application.impl;

import com.example.whopper.domain.file.application.usecase.PdfUseCase;
import com.example.whopper.domain.library.application.usecase.FindLibraryUseCase;
import com.example.whopper.domain.library.dao.LibraryMongoRepository;
import com.example.whopper.domain.library.domain.LibraryEntity;
import com.example.whopper.domain.library.dto.response.LibraryDetailResponse;
import com.example.whopper.domain.library.dto.response.LibraryResponse;
import com.example.whopper.domain.library.exception.LibraryNotFoundException;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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
