package com.dsm.repo.external.exception;

import com.dsm.repo.external.exception.error.ErrorCode;

public class ExternalException extends WhopperException {
    public ExternalException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
