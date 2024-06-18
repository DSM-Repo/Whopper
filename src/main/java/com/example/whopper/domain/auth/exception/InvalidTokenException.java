package com.example.whopper.domain.auth.exception;

import com.example.whopper.global.error.exception.ErrorCode;
import com.example.whopper.global.error.exception.WhopperException;

public class InvalidTokenException extends WhopperException {

    public static final WhopperException EXCEPTION = new InvalidTokenException();

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}