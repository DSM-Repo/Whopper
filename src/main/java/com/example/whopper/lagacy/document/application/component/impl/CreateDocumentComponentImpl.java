package com.example.whopper.lagacy.document.application.component.impl;

import com.example.whopper.lagacy.document.application.component.CreateDocumentComponent;
import com.example.whopper.lagacy.document.dao.DocumentRepository;
import com.example.whopper.lagacy.document.domain.DocumentEntity;
import com.example.whopper.lagacy.student.domain.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CreateDocumentComponentImpl implements CreateDocumentComponent {
    private final DocumentRepository documentRepository;

    @Override
    public DocumentEntity create(StudentEntity student) {
        var document = DocumentEntity.createForNewStudent(student);

        return documentRepository.save(document);
    }
}
