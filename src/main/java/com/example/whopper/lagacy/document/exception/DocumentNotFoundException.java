package com.example.whopper.lagacy.document.exception;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class DocumentNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new DocumentNotFoundException();

    private DocumentNotFoundException() {
        super(ErrorCode.DOCUMENT_NOT_FOUND);
    }
}
