package com.example.whopper.global.utils.current;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.student.domain.StudentEntity;

public interface CurrentStudent {
    StudentEntity getStudent();
    DocumentEntity getDocument();
}
