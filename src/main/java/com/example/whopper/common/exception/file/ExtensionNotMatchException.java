package com.example.whopper.common.exception.file;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class ExtensionNotMatchException extends WhopperException {
    public static final WhopperException EXCEPTION = new ExtensionNotMatchException();

    private ExtensionNotMatchException() {
        super(ErrorCode.EXTENSION_NOT_MATCH);
    }
}
