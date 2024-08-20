package com.example.whopper.infra.feign.exception;

import com.example.whopper.global.error.exception.ErrorCode;
import com.example.whopper.global.error.exception.WhopperException;

public class XquareException extends WhopperException {
    public static final WhopperException EXCEPTION = new XquareException();

    private XquareException() {
        super(ErrorCode.XQUARE);
    }

}
