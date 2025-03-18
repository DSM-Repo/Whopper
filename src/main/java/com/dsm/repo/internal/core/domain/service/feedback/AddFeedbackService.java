package com.dsm.repo.internal.core.domain.service.feedback;

import com.dsm.repo.internal.core.domain.event.teacher.CurrentTeacher;
import com.dsm.repo.internal.core.domain.model.feedback.FeedbackModel;
import com.dsm.repo.internal.data.repository.feedback.FeedbackRepository;
import com.dsm.repo.internal.data.repository.resume.ResumeRepository;
import com.dsm.repo.external.exception.domain.resume.ResumeIllegalStatusException;
import com.dsm.repo.external.exception.domain.resume.ResumeNotFoundException;
import com.dsm.repo.internal.core.usecase.feedback.AddFeedbackUseCase;
import com.dsm.repo.external.web.rest.feedback.dto.FeedbackElementDto;
import com.dsm.repo.external.web.rest.feedback.dto.request.FeedbackRequest;
import com.dsm.repo.external.web.rest.resume.dto.ResumeElementDto;
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

        if(resume.status() == ResumeElementDto.Status.RELEASED) throw ResumeIllegalStatusException.EXCEPTION;

        final var teacher = currentTeacher.getTeacher();

        feedbackRepository.save(new FeedbackModel(null, request.comment(), FeedbackElementDto.Type.valueOf(request.type()), request.resumeId(),
                FeedbackElementDto.Status.PENDING, false, new FeedbackElementDto.Writer(teacher.id(), teacher.name())));
    }
}
