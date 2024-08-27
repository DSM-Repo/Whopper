package com.example.whopper.application.student.component;

import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.common.exception.resume.ResumeNotFoundException;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.student.StudentModel;
import com.example.whopper.common.exception.student.StudentNotFoundException;
import com.example.whopper.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentStudentImpl implements CurrentStudent {
    private final StudentRepository studentRepository;
    private final ResumeRepository resumeRepository;

    @Override
    public StudentModel getStudent() {
        var id = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return studentRepository.findById(id)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
    }

    @Override
    public ResumeModel getResume() {
        return resumeRepository.findByWriterId(getStudent().id())
                .orElseThrow(() -> ResumeNotFoundException.EXCEPTION);
    }
}
