package com.dsm.repo.internal.core.domain.service.resume;

import com.dsm.repo.internal.core.usecase.resume.SubmitMyResumeUseCase;
import com.dsm.repo.external.exception.ForbiddenException;
import com.dsm.repo.internal.core.domain.model.resume.ResumeModel;
import com.dsm.repo.internal.data.repository.resume.ResumeRepository;
import com.dsm.repo.internal.core.domain.component.student.CurrentStudent;
import com.dsm.repo.external.web.rest.resume.dto.ResumeElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class SubmitMyResumeService implements SubmitMyResumeUseCase {
    private final ResumeRepository resumeRepository;
    private final CurrentStudent currentStudent;

    @Override
    @Transactional
    public void submit() {
        final var resume = currentStudent.getResume();

        ResumeModel newResume;
        if (resume.status().equals(ResumeElementDto.Status.SUBMITTED)) {
            newResume = cancelSubmit(resume);
        } else if(resume.status().equals(ResumeElementDto.Status.ONGOING)) {
            newResume = submit(resume);
        } else {
            throw ForbiddenException.EXCEPTION;
        }

        save(newResume);
    }

    private ResumeModel cancelSubmit(ResumeModel resume) {
        return resume.onGoing();
    }

    private ResumeModel submit(ResumeModel resume) {
        return resume.submit();
    }

    private void save(ResumeModel resume) {
        resumeRepository.save(resume);
    }
}
