package com.example.whopper.common.exception.resume;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class DocumentNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new DocumentNotFoundException();

    private DocumentNotFoundException() {
        super(ErrorCode.DOCUMENT_NOT_FOUND);
    }
}
