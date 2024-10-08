package com.repo.whopper.common.exception.library;

import com.repo.whopper.common.error.exception.ErrorCode;
import com.repo.whopper.common.error.exception.WhopperException;

public class LibraryNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new LibraryNotFoundException();

    private LibraryNotFoundException() {
        super(ErrorCode.LIBRARY_NOT_FOUND);
    }
}
