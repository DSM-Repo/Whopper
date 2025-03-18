package com.dsm.repo.external.exception.domain.resume;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class ResumeModificationException extends WhopperException {
    public static final WhopperException EXCEPTION = new ResumeModificationException();

    private ResumeModificationException() {
        super(ErrorCode.RESUME_MODIFICATION_NOT_ALLOWED);
    }
}
