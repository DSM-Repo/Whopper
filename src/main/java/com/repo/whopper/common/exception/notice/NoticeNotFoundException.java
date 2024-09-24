package com.repo.whopper.common.exception.notice;

import com.repo.whopper.common.error.exception.ErrorCode;
import com.repo.whopper.common.error.exception.WhopperException;

public class NoticeNotFoundException extends WhopperException {
    public static final WhopperException EXCEPTION = new NoticeNotFoundException();

    private NoticeNotFoundException() {
        super(ErrorCode.NOTICE_NOT_FOUND);
    }
}
