package com.example.whopper.application.feedback.service;

import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.element.DocumentStatus;
import com.example.whopper.common.exception.resume.DocumentIllegalStatusException;
import com.example.whopper.common.exception.resume.ResumeNotFoundException;
import com.example.whopper.application.feedback.usecase.AddFeedbackUseCase;
import com.example.whopper.domain.feedback.FeedbackMongoRepository;
import com.example.whopper.domain.feedback.FeedbackEntity;
import com.example.whopper.interfaces.feedback.dto.FeedbackRequest;
import com.example.whopper.application.teacher.component.TeacherComponent;
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
        DocumentEntity document = resumeRepository.findById(request.documentId())
                        .orElseThrow(()-> ResumeNotFoundException.EXCEPTION);

        if(document.getStatus() != DocumentStatus.SUBMITTED) throw DocumentIllegalStatusException.EXCEPTION;

        feedbackMongoRepository.save(
                FeedbackEntity.builder()
                        .comment(request.comment())
                        .type(request.type())
                        .documentId(document.getId())
                        .teacher(teacherComponent.currentTeacher())
                        .build());
    }
}
