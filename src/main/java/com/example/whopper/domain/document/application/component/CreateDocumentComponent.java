package com.example.whopper.domain.document.application.component;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.student.domain.StudentEntity;

public interface CreateDocumentComponent {
    DocumentEntity create(StudentEntity student);
}
