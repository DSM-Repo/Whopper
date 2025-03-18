package com.dsm.repo.external.exception.domain.resume;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class ImageRequestSizeMismatchException extends WhopperException {
    public static final WhopperException EXCEPTION = new ImageRequestSizeMismatchException();

    private ImageRequestSizeMismatchException() {
        super(ErrorCode.IMAGE_REQUEST_SIZE_MISMATCH);
    }
}
