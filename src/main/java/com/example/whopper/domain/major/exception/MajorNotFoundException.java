package com.example.whopper.domain.major.exception;

import com.example.whopper.global.error.exception.ErrorCode;
import com.example.whopper.global.error.exception.WhopperException;

public class MajorNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new MajorNotFoundException();

    private MajorNotFoundException() {
        super(ErrorCode.MAJOR_NOT_FOUND);
    }
}
