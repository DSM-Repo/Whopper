package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.usecase.ReleaseResumeUseCase;
import com.example.whopper.domain.feedback.FeedbackRepository;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.common.exception.resume.ResumeIllegalStatusException;
import com.example.whopper.common.exception.resume.ResumeNotFoundException;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class ReleaseResumeService implements ReleaseResumeUseCase {
    private final ResumeRepository resumeRepository;
    private final FeedbackRepository feedbackRepository;

    @Override
    public void release(String resumeId) {
        var resume = findById(resumeId);

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
