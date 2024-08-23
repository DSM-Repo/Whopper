package com.example.whopper.common.error.exception;

public class ForbiddenException extends WhopperException {
    public static final WhopperException EXCEPTION = new ForbiddenException();

    private ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
}
