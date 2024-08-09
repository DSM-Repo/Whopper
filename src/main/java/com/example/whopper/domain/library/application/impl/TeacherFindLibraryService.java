package com.example.whopper.domain.library.application.impl;

import com.example.whopper.domain.file.application.usecase.PdfUseCase;
import com.example.whopper.domain.library.application.usecase.TeacherFindLibraryUseCase;
import com.example.whopper.domain.library.dao.LibraryMongoRepository;
import com.example.whopper.domain.library.dto.response.LibraryResponse;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherFindLibraryService implements TeacherFindLibraryUseCase {

    private final LibraryMongoRepository libraryMongoRepository;
    private final PdfUseCase pdfUseCase;

    public DataResponseInfo<LibraryResponse> teacherFindLibrary(Integer year) {
        return DataResponseInfo.of(libraryMongoRepository.findFirstByYear(year)
                .stream()
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
