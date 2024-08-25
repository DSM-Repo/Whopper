package com.example.whopper.common.exception.resume;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class ResumeIllegalStatusException extends WhopperException {
    public static final WhopperException EXCEPTION = new ResumeIllegalStatusException();

    private ResumeIllegalStatusException() {
        super(ErrorCode.RESUME_ILLEGAL_STATUS);
    }
}
