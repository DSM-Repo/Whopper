package com.repo.whopper.common.exception.auth;

import com.repo.whopper.common.error.exception.ErrorCode;
import com.repo.whopper.common.error.exception.WhopperException;

public class InvalidTokenException extends WhopperException {

    public static final WhopperException EXCEPTION = new InvalidTokenException();

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}