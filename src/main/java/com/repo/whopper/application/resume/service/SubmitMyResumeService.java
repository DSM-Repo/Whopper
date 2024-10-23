package com.repo.whopper.application.resume.service;

import com.repo.whopper.application.resume.usecase.SubmitMyResumeUseCase;
import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.domain.resume.ResumeRepository;
import com.repo.whopper.application.student.component.CurrentStudent;
import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;
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
            newResume =cancelSubmit(resume);
        } if else(resume.status().equals(ResumeElementDto.Status.ONGOING)) {
            newResume = submit(resume);
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
