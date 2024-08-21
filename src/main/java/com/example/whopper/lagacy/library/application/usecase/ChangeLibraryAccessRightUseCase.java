package com.example.whopper.lagacy.library.application.usecase;

import com.example.whopper.lagacy.library.domain.type.AccessRight;

public interface ChangeLibraryAccessRightUseCase {
    void changeLibraryAccessRight(String libraryId, AccessRight accessRight);
}
