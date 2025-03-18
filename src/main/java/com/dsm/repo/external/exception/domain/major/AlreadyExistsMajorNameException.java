package com.dsm.repo.external.exception.domain.major;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class AlreadyExistsMajorNameException extends WhopperException {
    public static final WhopperException EXCEPTION = new AlreadyExistsMajorNameException();

    private AlreadyExistsMajorNameException() {
        super(ErrorCode.DUPLICATED_MAJOR);
    }
}
