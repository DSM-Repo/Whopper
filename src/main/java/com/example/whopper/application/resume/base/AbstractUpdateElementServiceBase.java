package com.example.whopper.application.resume.base;

import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.common.exception.resume.DocumentModificationException;
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
        var resume = currentStudent.getDocument();

        if (!resume.status().equals(ResumeElementDto.Status.ONGOING)) {
            throw DocumentModificationException.EXCEPTION;
        }

        var newResume = updateResume(resume, request);
        resumeRepository.save(newResume);
    }

    protected abstract ResumeModel updateResume(ResumeModel resume, T request);
}