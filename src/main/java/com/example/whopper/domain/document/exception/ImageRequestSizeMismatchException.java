package com.example.whopper.domain.document.exception;

import com.example.whopper.global.error.exception.ErrorCode;
import com.example.whopper.global.error.exception.WhopperException;

public class ImageRequestSizeMismatchException extends WhopperException {
    public static final WhopperException EXCEPTION = new ImageRequestSizeMismatchException();

    private ImageRequestSizeMismatchException() {
        super(ErrorCode.IMAGE_REQUEST_SIZE_MISMATCH);
    }
}
