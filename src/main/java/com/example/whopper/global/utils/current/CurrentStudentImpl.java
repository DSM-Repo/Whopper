package com.example.whopper.global.utils.current;

import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.exception.DocumentNotFoundException;
import com.example.whopper.domain.student.dao.StudentMongoRepository;
import com.example.whopper.domain.student.domain.StudentEntity;
import com.example.whopper.domain.student.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentStudentImpl implements CurrentStudent {
    private final StudentMongoRepository studentMongoRepository;
    private final DocumentRepository documentRepository;

    @Override
    public StudentEntity getStudent() {
        var id = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return studentMongoRepository.findById(id)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
    }

    @Override
    public DocumentEntity getDocument() {
        return documentRepository.findByWriterId(getStudent().getId())
                .orElseThrow(() -> DocumentNotFoundException.EXCEPTION);
    }
}
