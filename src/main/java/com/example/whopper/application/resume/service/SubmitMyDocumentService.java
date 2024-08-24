package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.usecase.SubmitMyDocumentUseCase;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.application.student.component.CurrentStudent;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class SubmitMyDocumentService implements SubmitMyDocumentUseCase {
    private final ResumeRepository resumeRepository;
    private final CurrentStudent currentStudent;

    @Override
    public void submit() {
        var resume = currentStudent.getDocument();

        ResumeModel newResume;
        if (resume.status().equals(ResumeElementDto.Status.SUBMITTED)) {
            newResume =cancelSubmit(resume);
        } else {
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
