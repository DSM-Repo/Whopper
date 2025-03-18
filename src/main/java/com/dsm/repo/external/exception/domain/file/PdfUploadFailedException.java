package com.dsm.repo.external.exception.domain.file;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class PdfUploadFailedException extends WhopperException {
    public static final WhopperException EXCEPTION = new PdfUploadFailedException();

    private PdfUploadFailedException() {
        super(ErrorCode.PDF_UPLOAD_FAILED);
    }
}
