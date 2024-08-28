package com.example.whopper.application.library.usecase;

import com.example.whopper.interfaces.library.dto.LibraryElementDto;

public interface ChangeLibraryAccessRightUseCase {
    void changeLibraryAccessRight(String libraryId, LibraryElementDto.AccessRight accessRight);
}