package com.example.whopper.common.exception.major;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class AlreadyExistsMajorNameException extends WhopperException {
    public static final WhopperException EXCEPTION = new AlreadyExistsMajorNameException();

    private AlreadyExistsMajorNameException() {
        super(ErrorCode.DUPLICATED_MAJOR);
    }
}
