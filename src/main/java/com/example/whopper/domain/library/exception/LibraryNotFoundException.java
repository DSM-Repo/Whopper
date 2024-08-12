package com.example.whopper.domain.library.exception;

import com.example.whopper.global.error.exception.ErrorCode;
import com.example.whopper.global.error.exception.WhopperException;

public class LibraryNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new LibraryNotFoundException();

    private LibraryNotFoundException() {
        super(ErrorCode.LIBRARY_NOT_FOUND);
    }
}
