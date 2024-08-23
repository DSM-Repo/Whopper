package com.example.whopper.application.resume.component.impl;

import com.example.whopper.application.resume.component.CreateDocumentComponent;
import com.example.whopper.domain.resume.DocumentRepository;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.student.StudentEntity;
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
