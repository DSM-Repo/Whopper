package com.repo.whopper.application.library.usecase;

import com.repo.whopper.interfaces.library.dto.LibraryElementDto;

public interface ChangeLibraryAccessRightUseCase {
    void changeLibraryAccessRight(String libraryId, LibraryElementDto.AccessRight accessRight);
}