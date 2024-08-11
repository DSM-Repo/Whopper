package com.example.whopper.domain.library.application.usecase;

import com.example.whopper.domain.library.domain.type.AccessRight;

public interface ChangeLibraryAccessRightUseCase {
    void changeLibraryAccessRight(String libraryId, AccessRight accessRight);
}
