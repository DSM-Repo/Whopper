package com.dsm.repo.external.exception.domain.teacher;

import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.WhopperException;

public class TeacherNotFoundException extends WhopperException {

    public static final WhopperException EXCEPTION = new TeacherNotFoundException();

    public TeacherNotFoundException() {
        super(ErrorCode.TEACHER_NOT_FOUND);
    }
}