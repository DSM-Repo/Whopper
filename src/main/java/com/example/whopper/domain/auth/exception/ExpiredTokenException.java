package com.example.whopper.domain.auth.exception;

import com.example.whopper.global.error.exception.ErrorCode;
import com.example.whopper.global.error.exception.WhopperException;

public class ExpiredTokenException extends WhopperException {

    public static final WhopperException EXCEPTION = new ExpiredTokenException();

    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}