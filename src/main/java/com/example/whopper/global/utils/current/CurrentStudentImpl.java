package com.example.whopper.global.utils.current;

import com.example.whopper.domain.resume.DocumentRepository;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.common.exception.resume.DocumentNotFoundException;
import com.example.whopper.domain.student.StudentMongoRepository;
import com.example.whopper.domain.student.StudentEntity;
import com.example.whopper.common.exception.student.StudentNotFoundException;
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
