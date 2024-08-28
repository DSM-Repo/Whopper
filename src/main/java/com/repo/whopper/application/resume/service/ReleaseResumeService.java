package com.repo.whopper.application.resume.service;

import com.repo.whopper.application.resume.usecase.ReleaseResumeUseCase;
import com.repo.whopper.domain.feedback.FeedbackRepository;
import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.domain.resume.ResumeRepository;
import com.repo.whopper.common.exception.resume.ResumeIllegalStatusException;
import com.repo.whopper.common.exception.resume.ResumeNotFoundException;
import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class ReleaseResumeService implements ReleaseResumeUseCase {
    private final ResumeRepository resumeRepository;
    private final FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public void release(String resumeId) {
        final var resume = findById(resumeId);

        ResumeModel newResume;
        if (resume.status().equals(ResumeElementDto.Status.RELEASED)) {
            newResume = cancelRelease(resume);
        } else if (resume.status().equals(ResumeElementDto.Status.SUBMITTED)) {
            deleteFeedback(resumeId);
            newResume = release(resume);
        } else {
            throw ResumeIllegalStatusException.EXCEPTION;
        }

        save(newResume);
    }

    private void deleteFeedback(String resumeId) {
        feedbackRepository.deleteAllByResumeId(resumeId);
    }

    private ResumeModel findById(String resumeId) {
        return resumeRepository.findById(resumeId)
                .orElseThrow(() -> ResumeNotFoundException.EXCEPTION);
    }

    private ResumeModel release(ResumeModel resume) {
        return resume.release();
    }

    private ResumeModel cancelRelease(ResumeModel resume) {
        return resume.submit();
    }

    private void save(ResumeModel resume) {
        resumeRepository.save(resume);
    }
}
