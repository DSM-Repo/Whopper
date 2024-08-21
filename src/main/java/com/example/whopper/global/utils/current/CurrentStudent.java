package com.example.whopper.global.utils.current;

import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.student.StudentEntity;

public interface CurrentStudent {
    StudentEntity getStudent();
    DocumentEntity getDocument();
}
