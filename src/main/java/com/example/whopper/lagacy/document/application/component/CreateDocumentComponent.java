package com.example.whopper.lagacy.document.application.component;

import com.example.whopper.lagacy.document.domain.DocumentEntity;
import com.example.whopper.lagacy.student.domain.StudentEntity;

public interface CreateDocumentComponent {
    DocumentEntity create(StudentEntity student);
}
