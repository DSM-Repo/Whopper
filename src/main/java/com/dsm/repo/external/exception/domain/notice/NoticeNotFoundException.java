package com.dsm.repo.external.exception.domain.notice;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class NoticeNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new NoticeNotFoundException();

    private NoticeNotFoundException() {
        super(ErrorCode.NOTICE_NOT_FOUND);
    }
}
