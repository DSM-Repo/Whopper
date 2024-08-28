package com.repo.whopper.common.exception.resume;

import com.repo.whopper.common.error.exception.ErrorCode;
import com.repo.whopper.common.error.exception.WhopperException;

public class ResumeNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new ResumeNotFoundException();

    private ResumeNotFoundException() {
        super(ErrorCode.RESUME_NOT_FOUND);
    }
}
