package com.example.whopper.common.exception.auth;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class RefreshTokenNotFoundException extends WhopperException {

    public static final WhopperException EXCEPTION = new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}