package com.example.whopper.domain.document.application.component.impl;

import com.example.whopper.domain.document.application.component.CreateDocumentComponent;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import com.example.whopper.domain.document.domain.element.WriterInfoElement;
import com.example.whopper.domain.student.domain.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateDocumentComponentImpl implements CreateDocumentComponent {
    private final DocumentRepository documentRepository;

    @Override
    public DocumentEntity create(StudentEntity student) {
        var document = createEntity(student);

        return documentRepository.save(document);
    }

    private static DocumentEntity createEntity(StudentEntity student) {
        return DocumentEntity.builder()
                .status(DocumentStatus.ONGOING)
                .writer(WriterInfoElement.createEmptyElement(student))
                .student(student)
                .build();
    }
}
