package com.repo.whopper.common.exception.external;

import com.repo.whopper.common.error.exception.ErrorCode;
import com.repo.whopper.common.error.exception.WhopperException;

public class ExternalException extends WhopperException {
    public ExternalException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
