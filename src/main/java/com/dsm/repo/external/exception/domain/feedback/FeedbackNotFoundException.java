package com.dsm.repo.external.exception.domain.feedback;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class FeedbackNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new FeedbackNotFoundException();

    private FeedbackNotFoundException() {
        super(ErrorCode.FEEDBACK_NOT_FOUND);
    }
}
