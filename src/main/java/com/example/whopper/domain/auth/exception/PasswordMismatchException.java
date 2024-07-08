package com.example.whopper.domain.auth.exception;

import com.example.whopper.global.error.exception.ErrorCode;
import com.example.whopper.global.error.exception.WhopperException;

public class PasswordMismatchException extends WhopperException {

    public static final WhopperException EXCEPTION = new PasswordMismatchException();

    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
