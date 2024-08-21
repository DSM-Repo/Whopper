package com.example.whopper.lagacy.feedback.application.impl;

import com.example.whopper.lagacy.document.dao.DocumentRepository;
import com.example.whopper.lagacy.document.domain.DocumentEntity;
import com.example.whopper.lagacy.document.domain.element.DocumentStatus;
import com.example.whopper.lagacy.document.exception.DocumentIllegalStatusException;
import com.example.whopper.lagacy.document.exception.DocumentNotFoundException;
import com.example.whopper.lagacy.feedback.application.usecase.AddFeedbackUseCase;
import com.example.whopper.lagacy.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.lagacy.feedback.domain.FeedbackEntity;
import com.example.whopper.lagacy.feedback.dto.FeedbackRequest;
import com.example.whopper.lagacy.teacher.application.component.TeacherComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddFeedbackService implements AddFeedbackUseCase {

    private final FeedbackMongoRepository feedbackMongoRepository;

    private final DocumentRepository documentRepository;

    private final TeacherComponent teacherComponent;

    @Override
    public void addFeedback(FeedbackRequest request) {
        DocumentEntity document = documentRepository.findById(request.documentId())
                        .orElseThrow(()-> DocumentNotFoundException.EXCEPTION);

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
