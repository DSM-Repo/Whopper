package com.example.whopper.application.resume.component;

import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.student.StudentEntity;

public interface CreateDocumentComponent {
    DocumentEntity create(StudentEntity student);
}
