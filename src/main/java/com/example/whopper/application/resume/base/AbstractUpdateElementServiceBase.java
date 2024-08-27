package com.example.whopper.application.resume.base;

import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.common.exception.resume.ResumeModificationException;
import com.example.whopper.application.student.component.CurrentStudent;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public abstract class AbstractUpdateElementServiceBase<T> {
    protected final ResumeRepository resumeRepository;
    protected final CurrentStudent currentStudent;

    @Transactional
    public void update(T request) {
        final var resume = currentStudent.getResume();

        if (!resume.status().equals(ResumeElementDto.Status.ONGOING)) {
            throw ResumeModificationException.EXCEPTION;
        }

        final var newResume = updateResume(resume, request);
        resumeRepository.save(newResume);
    }

    protected abstract ResumeModel updateResume(ResumeModel resume, T request);
}