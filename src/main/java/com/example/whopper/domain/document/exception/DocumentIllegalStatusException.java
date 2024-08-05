package com.example.whopper.domain.document.exception;

import com.example.whopper.global.error.exception.ErrorCode;
import com.example.whopper.global.error.exception.WhopperException;

public class DocumentIllegalStatusException extends WhopperException {
    public static final WhopperException EXCEPTION = new DocumentIllegalStatusException();

    private DocumentIllegalStatusException() {
        super(ErrorCode.DOCUMENT_ILLEGAL_STATUS);
    }
}
