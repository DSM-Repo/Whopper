package com.example.whopper.application.student.component;

import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.student.StudentEntity;

public interface CurrentStudent {
    StudentEntity getStudent();
    DocumentEntity getDocument();
}
