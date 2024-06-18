package com.example.whopper.domain.student.exception;

import com.example.whopper.global.error.exception.ErrorCode;
import com.example.whopper.global.error.exception.WhopperException;

public class StudentNotFoundException extends WhopperException {

    public static final WhopperException EXCEPTION = new StudentNotFoundException();

    public StudentNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}