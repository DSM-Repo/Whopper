package com.repo.whopper.common.exception.student;

import com.repo.whopper.common.error.exception.ErrorCode;
import com.repo.whopper.common.error.exception.WhopperException;

public class StudentNotFoundException extends WhopperException {

    public static final WhopperException EXCEPTION = new StudentNotFoundException();

    public StudentNotFoundException() {
        super(ErrorCode.STUDENT_NOT_FOUND);
    }
}