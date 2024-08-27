package com.example.whopper.application.library.impl;

import com.example.whopper.application.library.usecase.ChangeLibraryAccessRightUseCase;
import com.example.whopper.domain.library.LibraryMongoRepository;
import com.example.whopper.domain.library.LibraryEntity;
import com.example.whopper.domain.library.type.AccessRight;
import com.example.whopper.common.exception.library.LibraryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangeLibraryAccessRightService implements ChangeLibraryAccessRightUseCase {

    private final LibraryMongoRepository libraryMongoRepository;

    @Override
    @Transactional
    public void changeLibraryAccessRight(String libraryId, AccessRight accessRight) {
        LibraryEntity library = libraryMongoRepository.findById(libraryId)
                .orElseThrow(() -> LibraryNotFoundException.EXCEPTION);

        library.changeAccessRight(accessRight);
        libraryMongoRepository.save(library);
    }
}
