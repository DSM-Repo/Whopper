package com.dsm.repo.internal.core.domain.service.library;

import com.dsm.repo.internal.core.usecase.file.PdfUseCase;
import com.dsm.repo.internal.core.usecase.library.FindLibraryUseCase;
import com.dsm.repo.internal.core.domain.model.library.LibraryModel;
import com.dsm.repo.internal.data.repository.library.LibraryRepository;
import com.dsm.repo.external.web.rest.library.dto.response.LibraryDetailResponse;
import com.dsm.repo.external.web.rest.library.dto.response.LibraryResponse;
import com.dsm.repo.external.exception.domain.library.LibraryNotFoundException;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;
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
    public LibraryDetailResponse findPublicLibrary(final String libraryId) {
        LibraryModel library = libraryRepository.findPublicById(libraryId)
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