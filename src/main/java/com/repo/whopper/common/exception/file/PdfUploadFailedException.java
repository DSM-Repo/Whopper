package com.repo.whopper.common.exception.file;

import com.repo.whopper.common.error.exception.ErrorCode;
import com.repo.whopper.common.error.exception.WhopperException;

public class PdfUploadFailedException extends WhopperException {
    public static final WhopperException EXCEPTION = new PdfUploadFailedException();

    private PdfUploadFailedException() {
        super(ErrorCode.PDF_UPLOAD_FAILED);
    }
}
