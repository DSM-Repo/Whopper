package com.dsm.repo.external.exception.domain.resume;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class ResumeNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new ResumeNotFoundException();

    private ResumeNotFoundException() {
        super(ErrorCode.RESUME_NOT_FOUND);
    }
}
