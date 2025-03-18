package com.dsm.repo.external.xquare.exception;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class XquareException extends WhopperException {
    public static final WhopperException EXCEPTION = new XquareException();

    private XquareException() {
        super(ErrorCode.XQUARE);
    }
}
