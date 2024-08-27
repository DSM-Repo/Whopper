package com.example.whopper.application.library.usecase;

import com.example.whopper.domain.library.type.AccessRight;

public interface ChangeLibraryAccessRightUseCase {
    void changeLibraryAccessRight(String libraryId, AccessRight accessRight);
}
