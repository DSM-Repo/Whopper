package com.repo.whopper.application.student.component;

import com.repo.whopper.domain.resume.ResumeRepository;
import com.repo.whopper.common.exception.resume.ResumeNotFoundException;
import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.domain.student.StudentModel;
import com.repo.whopper.common.exception.student.StudentNotFoundException;
import com.repo.whopper.domain.student.StudentRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CurrentStudentImpl implements CurrentStudent {
    private final StudentRepository studentRepository;
    private final ResumeRepository resumeRepository;

    @Override
    @Transactional
    public StudentModel getStudent() {
        final var id = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return studentRepository.findById(id)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
    }

    @Override
    @Transactional
    public ResumeModel getResume() {
        return resumeRepository.findByWriterId(this.getStudent().id())
                .orElseThrow(() -> ResumeNotFoundException.EXCEPTION);
    }

    @Override
    @Transactional
    public ResumeModel getResume(String studentId) {
        return resumeRepository.findByWriterId(studentId)
                .orElseThrow(() -> ResumeNotFoundException.EXCEPTION);
    }
}
