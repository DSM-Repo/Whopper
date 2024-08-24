package com.example.whopper.common.exception.resume;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class ImageRequestSizeMismatchException extends WhopperException {
    public static final WhopperException EXCEPTION = new ImageRequestSizeMismatchException();

    private ImageRequestSizeMismatchException() {
        super(ErrorCode.IMAGE_REQUEST_SIZE_MISMATCH);
    }
}