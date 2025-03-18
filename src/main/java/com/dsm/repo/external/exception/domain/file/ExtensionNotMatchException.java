package com.dsm.repo.external.exception.domain.file;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class ExtensionNotMatchException extends WhopperException {
    public static final WhopperException EXCEPTION = new ExtensionNotMatchException();

    private ExtensionNotMatchException() {
        super(ErrorCode.EXTENSION_NOT_MATCH);
    }
}
