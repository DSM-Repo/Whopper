package com.example.whopper.lagacy.feedback.exception;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class FeedbackNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new FeedbackNotFoundException();

    private FeedbackNotFoundException() {
        super(ErrorCode.FEEDBACK_NOT_FOUND);
    }
}
