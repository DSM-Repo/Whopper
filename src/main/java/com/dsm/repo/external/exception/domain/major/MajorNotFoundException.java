package com.dsm.repo.external.exception.domain.major;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class MajorNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new MajorNotFoundException();

    private MajorNotFoundException() {
        super(ErrorCode.MAJOR_NOT_FOUND);
    }
}
