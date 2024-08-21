package com.example.whopper.lagacy.library.application.dao;

import com.example.whopper.lagacy.file.application.usecase.PdfUseCase;
import com.example.whopper.lagacy.library.application.model.Library;
import com.example.whopper.lagacy.library.dao.LibraryMongoRepository;
import com.example.whopper.lagacy.library.domain.type.AccessRight;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LibraryAdapter {

    private final LibraryMongoRepository libraryMongoRepository;
    private final PdfUseCase pdfUseCase;

    public Optional<Library> getLibrary(AccessRight accessRight, @Nullable Integer year) {
        if (year == null) {
            return libraryMongoRepository.findFirstByAccessRightNot(accessRight)
                    .map(it -> {
                                var pdfFileUrl = pdfUseCase.getPdfFileUrl(it.getFilePath());
                                return it.toModel(pdfFileUrl);
                            }
                    );
        }

        return libraryMongoRepository.findFirstByAccessRightNotAndYear(
                accessRight, year
        ).map(it -> {
                    var pdfFileUrl = pdfUseCase.getPdfFileUrl(it.getFilePath());
                    return it.toModel(pdfFileUrl);
                }
        );
    }

}
