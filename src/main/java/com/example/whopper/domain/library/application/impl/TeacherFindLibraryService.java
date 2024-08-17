package com.example.whopper.domain.library.application.impl;

import com.example.whopper.domain.file.application.usecase.PdfUseCase;
import com.example.whopper.domain.library.application.usecase.TeacherFindLibraryUseCase;
import com.example.whopper.domain.library.dao.LibraryMongoRepository;
import com.example.whopper.domain.library.domain.type.AccessRight;
import com.example.whopper.domain.library.dto.LibraryResponse;
import com.example.whopper.global.utils.DataResponseInfo;
import com.mongodb.lang.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherFindLibraryService implements TeacherFindLibraryUseCase {

    private final LibraryMongoRepository libraryMongoRepository;
    private final PdfUseCase pdfUseCase;

    public DataResponseInfo<LibraryResponse> teacherFindLibrary(@Nullable Integer year) {
        var libraries = year == null
                ? libraryMongoRepository.findFirstByAccessRightNot(AccessRight.PRIVATE)
                : libraryMongoRepository.findFirstByAccessRightNotAndYear(AccessRight.PRIVATE, year);

        return DataResponseInfo.of(libraries.stream()
                .map(library -> new LibraryResponse(
                        library.getId(),
                        library.getAccessRight(),
                        library.getYear(),
                        library.getGrade(),
                        library.getGeneration(),
                        pdfUseCase.getPdfFileUrl(library.getFilePath())
                ))
                .toList());
    }
}
