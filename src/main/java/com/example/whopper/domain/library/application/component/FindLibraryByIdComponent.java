package com.example.whopper.domain.library.application.component;

import com.example.whopper.domain.library.domain.LibraryEntity;

public interface FindLibraryByIdComponent {
    LibraryEntity findLibraryById(String libraryId);
}
