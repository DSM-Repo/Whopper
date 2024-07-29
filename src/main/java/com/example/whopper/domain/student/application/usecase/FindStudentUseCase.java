package com.example.whopper.domain.student.application.usecase;

import com.example.whopper.domain.student.domain.StudentInfo;
import com.example.whopper.global.utils.DataResponseInfo;

public interface FindStudentUseCase {
    DataResponseInfo<StudentInfo> findStudentWithNoSubmittedDocumentStatus();
}
