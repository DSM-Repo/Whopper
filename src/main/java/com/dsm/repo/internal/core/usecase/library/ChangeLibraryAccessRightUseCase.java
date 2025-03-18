package com.dsm.repo.internal.core.usecase.library;

import com.dsm.repo.external.web.rest.library.dto.LibraryElementDto;

public interface ChangeLibraryAccessRightUseCase {
    void changeLibraryAccessRight(String libraryId, LibraryElementDto.AccessRight accessRight);
}