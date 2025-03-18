package com.dsm.repo.external.exception;

import com.dsm.repo.external.exception.error.ErrorCode;

public class ForbiddenException extends WhopperException {
    public static final WhopperException EXCEPTION = new ForbiddenException();

    private ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
}
