package com.example.whopper.domain.feedback.exception;

import com.example.whopper.global.error.exception.ErrorCode;
import com.example.whopper.global.error.exception.WhopperException;

public class FeedbackNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new FeedbackNotFoundException();

    private FeedbackNotFoundException() {
        super(ErrorCode.DOCUMENT_NOT_FOUND);
    }
}
