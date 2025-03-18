package com.dsm.repo.external.exception;

import com.dsm.repo.external.exception.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WhopperException extends RuntimeException {
    private final ErrorCode errorCode;
}
