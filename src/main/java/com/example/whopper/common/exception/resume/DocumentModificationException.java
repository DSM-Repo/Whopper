package com.example.whopper.common.exception.resume;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class DocumentModificationException extends WhopperException {
    public static final WhopperException EXCEPTION = new DocumentModificationException();

    private DocumentModificationException() {
        super(ErrorCode.DOCUMENT_MODIFICATION_NOT_ALLOWED);
    }
}
