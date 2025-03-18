package com.dsm.repo.external.exception.domain.auth;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class ExpiredTokenException extends WhopperException {

    public static final WhopperException EXCEPTION = new ExpiredTokenException();

    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}