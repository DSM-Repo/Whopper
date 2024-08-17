package com.example.whopper.domain.feedback.application.impl;

import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import com.example.whopper.domain.document.exception.DocumentIllegalStatusException;
import com.example.whopper.domain.document.exception.DocumentNotFoundException;
import com.example.whopper.domain.feedback.application.usecase.AddFeedbackUseCase;
import com.example.whopper.domain.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.domain.feedback.domain.FeedbackEntity;
import com.example.whopper.domain.feedback.dto.FeedbackRequest;
import com.example.whopper.domain.teacher.application.component.TeacherComponent;
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
        DocumentEntity document = documentRepository.findById(request.document_id())
                        .orElseThrow(()-> DocumentNotFoundException.EXCEPTION);

        if(document.getStatus() != DocumentStatus.SUBMITTED) throw DocumentIllegalStatusException.EXCEPTION;

        feedbackMongoRepository.save(
                FeedbackEntity.builder()
                        .comment(request.comment())
                        .writerName(teacherComponent.currentTeacher().getName())
                        .elementId(request.element_id())
                        .documentId(document.getId())
                        .build());
    }
}
