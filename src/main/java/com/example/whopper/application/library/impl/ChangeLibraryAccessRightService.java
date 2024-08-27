package com.example.whopper.application.library.impl;

import com.example.whopper.application.library.usecase.ChangeLibraryAccessRightUseCase;
import com.example.whopper.domain.library.LibraryModel;
import com.example.whopper.domain.library.LibraryRepository;
import com.example.whopper.common.exception.library.LibraryNotFoundException;
import com.example.whopper.interfaces.library.dto.LibraryElementDto;
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
        LibraryModel library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> LibraryNotFoundException.EXCEPTION);

        libraryRepository.save(library.changeAccessRight(accessRight));
    }
}
