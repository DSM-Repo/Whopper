package com.example.whopper.domain.library.application.impl;

import com.example.whopper.domain.library.application.component.FindLibraryByIdComponent;
import com.example.whopper.domain.library.application.usecase.ChangeLibraryAccessRightUseCase;
import com.example.whopper.domain.library.dao.LibraryMongoRepository;
import com.example.whopper.domain.library.domain.LibraryEntity;
import com.example.whopper.domain.library.domain.type.AccessRight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeLibraryAccessRightService implements ChangeLibraryAccessRightUseCase {

    private final FindLibraryByIdComponent findLibraryByIdComponent;
    private final LibraryMongoRepository libraryMongoRepository;

    public void changeLibraryAccessRight(String libraryId, AccessRight accessRight) {
        LibraryEntity library = findLibraryByIdComponent.findLibraryById(libraryId);

        library.changeAccessRight(accessRight);
        libraryMongoRepository.save(library);
    }
}
