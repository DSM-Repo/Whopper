package com.example.whopper.lagacy.teacher.exception;

import com.example.whopper.common.error.exception.ErrorCode;
import com.example.whopper.common.error.exception.WhopperException;

public class TeacherNotFoundException extends WhopperException {

    public static final WhopperException EXCEPTION = new TeacherNotFoundException();

    public TeacherNotFoundException() {
        super(ErrorCode.TEACHER_NOT_FOUND);
    }
}