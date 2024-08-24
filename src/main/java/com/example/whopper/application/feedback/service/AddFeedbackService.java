package com.example.whopper.application.feedback.service;

import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.common.exception.resume.DocumentIllegalStatusException;
import com.example.whopper.common.exception.resume.ResumeNotFoundException;
import com.example.whopper.application.feedback.usecase.AddFeedbackUseCase;
import com.example.whopper.domain.feedback.FeedbackMongoRepository;
import com.example.whopper.domain.feedback.FeedbackEntity;
import com.example.whopper.interfaces.feedback.dto.FeedbackRequest;
import com.example.whopper.application.teacher.component.TeacherComponent;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddFeedbackService implements AddFeedbackUseCase {

    private final FeedbackMongoRepository feedbackMongoRepository;

    private final ResumeRepository resumeRepository;

    private final TeacherComponent teacherComponent;

    @Override
    public void addFeedback(FeedbackRequest request) {
        var resume = resumeRepository.findById(request.documentId())
                        .orElseThrow(()-> ResumeNotFoundException.EXCEPTION);

        if(resume.status() != ResumeElementDto.Status.SUBMITTED) throw DocumentIllegalStatusException.EXCEPTION;

        feedbackMongoRepository.save(
                FeedbackEntity.builder()
                        .comment(request.comment())
                        .type(FeedbackEntity.Type.valueOf(request.type()))
                        .documentId(resume.id())
                        .teacher(teacherComponent.currentTeacher())
                        .build());
    }
}
