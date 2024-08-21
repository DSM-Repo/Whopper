package com.example.whopper.lagacy.major.exception;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class MajorNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new MajorNotFoundException();

    private MajorNotFoundException() {
        super(ErrorCode.MAJOR_NOT_FOUND);
    }
}
