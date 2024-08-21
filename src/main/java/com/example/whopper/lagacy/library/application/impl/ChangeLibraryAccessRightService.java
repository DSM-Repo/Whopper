package com.example.whopper.lagacy.library.application.impl;

import com.example.whopper.lagacy.library.application.usecase.ChangeLibraryAccessRightUseCase;
import com.example.whopper.lagacy.library.dao.LibraryMongoRepository;
import com.example.whopper.lagacy.library.domain.LibraryEntity;
import com.example.whopper.lagacy.library.domain.type.AccessRight;
import com.example.whopper.lagacy.library.exception.LibraryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangeLibraryAccessRightService implements ChangeLibraryAccessRightUseCase {

    private final LibraryMongoRepository libraryMongoRepository;

    @Transactional
    public void changeLibraryAccessRight(String libraryId, AccessRight accessRight) {
        LibraryEntity library = libraryMongoRepository.findById(libraryId)
                .orElseThrow(() -> LibraryNotFoundException.EXCEPTION);

        library.changeAccessRight(accessRight);
        libraryMongoRepository.save(library);
    }
}
