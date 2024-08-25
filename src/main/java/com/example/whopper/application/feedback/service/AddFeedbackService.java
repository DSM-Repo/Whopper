package com.example.whopper.application.feedback.service;

import com.example.whopper.domain.feedback.FeedbackModel;
import com.example.whopper.domain.feedback.FeedbackRepository;
import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.common.exception.resume.ResumeIllegalStatusException;
import com.example.whopper.common.exception.resume.ResumeNotFoundException;
import com.example.whopper.application.feedback.usecase.AddFeedbackUseCase;
import com.example.whopper.interfaces.feedback.dto.FeedbackElementDto;
import com.example.whopper.interfaces.feedback.dto.request.FeedbackRequest;
import com.example.whopper.application.teacher.component.TeacherComponent;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddFeedbackService implements AddFeedbackUseCase {

    private final FeedbackRepository feedbackRepository;

    private final ResumeRepository resumeRepository;

    private final TeacherComponent teacherComponent;

    @Override
    public void addFeedback(FeedbackRequest request) {
        final var resume = resumeRepository.findById(request.resumeId())
                        .orElseThrow(()-> ResumeNotFoundException.EXCEPTION);

        if(resume.status() != ResumeElementDto.Status.SUBMITTED) throw ResumeIllegalStatusException.EXCEPTION;

        final var teacher = teacherComponent.currentTeacher();

        feedbackRepository.save(new FeedbackModel(null, request.comment(), FeedbackElementDto.Type.valueOf(request.type()), request.resumeId(),
                FeedbackElementDto.Status.PENDING, false, new FeedbackElementDto.Writer(teacher.getId(), teacher.getName())));
    }
}
