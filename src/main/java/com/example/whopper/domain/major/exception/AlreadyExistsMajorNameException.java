package com.example.whopper.domain.major.exception;

import com.example.whopper.global.error.exception.ErrorCode;
import com.example.whopper.global.error.exception.WhopperException;

public class AlreadyExistsMajorNameException extends WhopperException {
    public static final WhopperException EXCEPTION = new AlreadyExistsMajorNameException();

    private AlreadyExistsMajorNameException() {
        super(ErrorCode.DUPLICATED_MAJOR);
    }
}
