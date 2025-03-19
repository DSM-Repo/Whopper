package com.dsm.repo.internal.core.domain.component.student.impl;

import com.dsm.repo.internal.core.domain.component.student.CurrentStudent;
import com.dsm.repo.internal.data.repository.resume.ResumeRepository;
import com.dsm.repo.external.exception.domain.resume.ResumeNotFoundException;
import com.dsm.repo.internal.core.domain.model.resume.ResumeModel;
import com.dsm.repo.internal.core.domain.model.student.StudentModel;
import com.dsm.repo.external.exception.domain.student.StudentNotFoundException;
import com.dsm.repo.internal.data.repository.student.StudentRepository;
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
        return resumeRepository.findByWriterAccountId(getStudent().accountId())
                .orElseThrow(() -> ResumeNotFoundException.EXCEPTION);
    }

    @Override
    @Transactional
    public ResumeModel getResume(String accountId) {
        return resumeRepository.findByWriterAccountId(accountId)
                .orElseThrow(() -> ResumeNotFoundException.EXCEPTION);
    }

}
