package com.example.whopper.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WhopperException extends RuntimeException {
    private final ErrorCode errorCode;
}
