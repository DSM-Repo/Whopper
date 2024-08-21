package com.example.whopper.common.exception.resume;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class DocumentIllegalStatusException extends WhopperException {
    public static final WhopperException EXCEPTION = new DocumentIllegalStatusException();

    private DocumentIllegalStatusException() {
        super(ErrorCode.DOCUMENT_ILLEGAL_STATUS);
    }
}
