package com.example.whopper.common.exception.resume;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class ResumeModificationException extends WhopperException {
    public static final WhopperException EXCEPTION = new ResumeModificationException();

    private ResumeModificationException() {
        super(ErrorCode.RESUME_MODIFICATION_NOT_ALLOWED);
    }
}
