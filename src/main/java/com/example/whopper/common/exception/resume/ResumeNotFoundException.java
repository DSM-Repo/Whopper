package com.example.whopper.common.exception.resume;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class ResumeNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new ResumeNotFoundException();

    private ResumeNotFoundException() {
        super(ErrorCode.DOCUMENT_NOT_FOUND);
    }
}
