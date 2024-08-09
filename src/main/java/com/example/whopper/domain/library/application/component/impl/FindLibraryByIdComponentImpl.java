package com.example.whopper.domain.library.application.component.impl;

import com.example.whopper.domain.library.dao.LibraryMongoRepository;
import com.example.whopper.domain.library.domain.LibraryEntity;
import com.example.whopper.domain.library.exception.LibraryNotFoundException;
import com.example.whopper.domain.library.application.component.FindLibraryByIdComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindLibraryByIdComponentImpl implements FindLibraryByIdComponent {

    private final LibraryMongoRepository libraryMongoRepository;

    public LibraryEntity findLibraryById(String libraryId) {
        return libraryMongoRepository.findById(libraryId)
                .orElseThrow(()-> LibraryNotFoundException.EXCEPTION);
    }
}
