package com.example.whopper.domain.library.application.impl;

import com.example.whopper.domain.file.application.usecase.PdfUseCase;
import com.example.whopper.domain.library.application.usecase.StudentFindLibraryUseCase;
import com.example.whopper.domain.library.dao.LibraryMongoRepository;
import com.example.whopper.domain.library.domain.type.AccessRight;
import com.example.whopper.domain.library.dto.response.LibraryResponse;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentFindLibraryService implements StudentFindLibraryUseCase {

    private final LibraryMongoRepository libraryMongoRepository;
    private final PdfUseCase pdfUseCase;

    public DataResponseInfo<LibraryResponse> studentFindLibrary(Integer year) {
        var libraries = libraryMongoRepository.findFirstByAccessRightNotAndYear(AccessRight.PRIVATE, year)
                .stream()
                .map(library -> new LibraryResponse(
                        null,
                        null,
                        library.getYear(),
                        library.getGrade(),
                        library.getGeneration(),
                        pdfUseCase.getPdfFileUrl(library.getFilePath())
                ))
                .toList();

        return DataResponseInfo.of(libraries);
    }
}
