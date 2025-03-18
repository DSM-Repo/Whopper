package com.dsm.repo.external.exception.domain.resume;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class ResumeIllegalStatusException extends WhopperException {
    public static final WhopperException EXCEPTION = new ResumeIllegalStatusException();

    private ResumeIllegalStatusException() {
        super(ErrorCode.RESUME_ILLEGAL_STATUS);
    }
}
