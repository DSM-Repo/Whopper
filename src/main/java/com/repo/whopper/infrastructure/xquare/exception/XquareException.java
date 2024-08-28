package com.repo.whopper.infrastructure.xquare.exception;

import com.repo.whopper.common.error.exception.ErrorCode;
import com.repo.whopper.common.error.exception.WhopperException;

public class XquareException extends WhopperException {
    public static final WhopperException EXCEPTION = new XquareException();

    private XquareException() {
        super(ErrorCode.XQUARE);
    }
}
