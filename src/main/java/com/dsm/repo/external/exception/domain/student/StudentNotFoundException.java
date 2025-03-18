package com.dsm.repo.external.exception.domain.student;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class StudentNotFoundException extends WhopperException {

    public static final WhopperException EXCEPTION = new StudentNotFoundException();

    public StudentNotFoundException() {
        super(ErrorCode.STUDENT_NOT_FOUND);
    }
}