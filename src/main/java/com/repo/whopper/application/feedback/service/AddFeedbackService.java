package com.repo.whopper.application.feedback.service;

import com.repo.whopper.application.teacher.component.CurrentTeacher;
import com.repo.whopper.domain.feedback.FeedbackModel;
import com.repo.whopper.domain.feedback.FeedbackRepository;
import com.repo.whopper.domain.resume.ResumeRepository;
import com.repo.whopper.common.exception.resume.ResumeIllegalStatusException;
import com.repo.whopper.common.exception.resume.ResumeNotFoundException;
import com.repo.whopper.application.feedback.usecase.AddFeedbackUseCase;
import com.repo.whopper.interfaces.feedback.dto.FeedbackElementDto;
import com.repo.whopper.interfaces.feedback.dto.request.FeedbackRequest;
import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddFeedbackService implements AddFeedbackUseCase {
    private final FeedbackRepository feedbackRepository;
    private final ResumeRepository resumeRepository;
    private final CurrentTeacher currentTeacher;

    @Override
    @Transactional
    public void addFeedback(FeedbackRequest request) {
        final var resume = resumeRepository.findById(request.resumeId())
                        .orElseThrow(()-> ResumeNotFoundException.EXCEPTION);

        if(resume.status() != ResumeElementDto.Status.SUBMITTED) throw ResumeIllegalStatusException.EXCEPTION;

        final var teacher = currentTeacher.getTeacher();

        feedbackRepository.save(new FeedbackModel(null, request.comment(), FeedbackElementDto.Type.valueOf(request.type()), request.resumeId(),
                FeedbackElementDto.Status.PENDING, false, new FeedbackElementDto.Writer(teacher.id(), teacher.name())));
    }
}
