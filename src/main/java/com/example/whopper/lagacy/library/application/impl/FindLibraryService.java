package com.example.whopper.lagacy.library.application.impl;

import com.example.whopper.lagacy.file.application.usecase.PdfUseCase;
import com.example.whopper.lagacy.library.application.usecase.FindLibraryUseCase;
import com.example.whopper.lagacy.library.dao.LibraryMongoRepository;
import com.example.whopper.lagacy.library.domain.LibraryEntity;
import com.example.whopper.lagacy.library.dto.response.LibraryDetailResponse;
import com.example.whopper.lagacy.library.dto.response.LibraryResponse;
import com.example.whopper.lagacy.library.exception.LibraryNotFoundException;
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
