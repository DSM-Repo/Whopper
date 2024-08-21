package com.example.whopper.global.utils.current;

import com.example.whopper.lagacy.document.domain.DocumentEntity;
import com.example.whopper.lagacy.student.domain.StudentEntity;

public interface CurrentStudent {
    StudentEntity getStudent();
    DocumentEntity getDocument();
}
