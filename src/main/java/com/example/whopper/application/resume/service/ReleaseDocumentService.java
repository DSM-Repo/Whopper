package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.usecase.ReleaseDocumentUseCase;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.common.exception.resume.DocumentIllegalStatusException;
import com.example.whopper.common.exception.resume.ResumeNotFoundException;
import com.example.whopper.domain.feedback.FeedbackMongoRepository;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class ReleaseDocumentService implements ReleaseDocumentUseCase {
    private final ResumeRepository resumeRepository;
    private final FeedbackMongoRepository feedbackMongoRepository;

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
            throw DocumentIllegalStatusException.EXCEPTION;
        }

        save(newResume);
    }

    private void deleteFeedback(String resumeId) {
        feedbackMongoRepository.deleteAllByDocumentId(resumeId);
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
