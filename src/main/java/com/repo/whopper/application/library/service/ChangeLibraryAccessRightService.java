package com.repo.whopper.application.library.service;

import com.repo.whopper.application.library.usecase.ChangeLibraryAccessRightUseCase;
import com.repo.whopper.domain.library.LibraryRepository;
import com.repo.whopper.common.exception.library.LibraryNotFoundException;
import com.repo.whopper.interfaces.library.dto.LibraryElementDto;
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

        library.changeAccessRight(accessRight);
        libraryRepository.save(library);
    }
}