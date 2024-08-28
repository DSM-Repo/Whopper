package com.example.whopper.infrastructure.xquare.exception;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class XquareException extends WhopperException {
    public static final WhopperException EXCEPTION = new XquareException();

    private XquareException() {
        super(ErrorCode.XQUARE);
    }
}
