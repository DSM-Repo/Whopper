package com.repo.whopper.application.resume.base;

import com.repo.whopper.domain.resume.ResumeRepository;
import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.common.exception.resume.ResumeModificationException;
import com.repo.whopper.application.student.component.CurrentStudent;
import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public abstract class AbstractUpdateElementServiceBase<T> {
    protected final ResumeRepository resumeRepository;
    protected final CurrentStudent currentStudent;

    @Transactional
    public void update(T request) {
        var resume = currentStudent.getResume();

        if (!resume.status().equals(ResumeElementDto.Status.ONGOING)) {
            throw ResumeModificationException.EXCEPTION;
        }

        var newResume = updateResume(resume, request);
        resumeRepository.save(newResume);
    }

    protected abstract ResumeModel updateResume(ResumeModel resume, T request);
}