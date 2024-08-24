package com.example.whopper.application.student.component;

import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.common.exception.resume.ResumeNotFoundException;
import com.example.whopper.domain.resume.ResumeModel;
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
    private final ResumeRepository resumeRepository;

    @Override
    public StudentEntity getStudent() {
        var id = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return studentMongoRepository.findById(id)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
    }

    @Override
    public ResumeModel getDocument() {
        return resumeRepository.findByWriterId(getStudent().getId())
                .orElseThrow(() -> ResumeNotFoundException.EXCEPTION);
    }
}
