package com.dsm.repo.internal.core.domain.service.library;

import com.dsm.repo.internal.core.usecase.library.ChangeLibraryAccessRightUseCase;
import com.dsm.repo.internal.data.repository.library.LibraryRepository;
import com.dsm.repo.external.exception.domain.library.LibraryNotFoundException;
import com.dsm.repo.external.web.rest.library.dto.LibraryElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangeLibraryAccessRightService implements ChangeLibraryAccessRightUseCase {
    private final LibraryRepository libraryRepository;

    @Override
    @Transactional
    public void changeLibraryAccessRight(String libraryId, LibraryElementDto.AccessRight accessRight) {
        var library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> LibraryNotFoundException.EXCEPTION);

        var newLibrary = library.changeAccessRight(accessRight);
        libraryRepository.save(newLibrary);
    }
}