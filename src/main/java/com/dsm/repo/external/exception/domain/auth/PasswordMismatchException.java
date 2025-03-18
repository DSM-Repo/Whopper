package com.dsm.repo.external.exception.domain.auth;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class PasswordMismatchException extends WhopperException {

    public static final WhopperException EXCEPTION = new PasswordMismatchException();

    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
