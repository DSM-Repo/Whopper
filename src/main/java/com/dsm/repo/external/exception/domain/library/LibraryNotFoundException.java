package com.dsm.repo.external.exception.domain.library;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class LibraryNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new LibraryNotFoundException();

    private LibraryNotFoundException() {
        super(ErrorCode.LIBRARY_NOT_FOUND);
    }
}
