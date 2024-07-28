package com.example.whopper.domain.document.exception;

import com.example.whopper.global.error.exception.ErrorCode;
import com.example.whopper.global.error.exception.WhopperException;

public class DocumentModificationException extends WhopperException {
    public static final WhopperException EXCEPTION = new DocumentModificationException();

    private DocumentModificationException() {
        super(ErrorCode.DOCUMENT_MODIFICATION_NOT_ALLOWED);
    }
}
