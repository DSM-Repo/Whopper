package com.example.whopper.domain.feedback.application.impl;

import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.exception.DocumentNotFoundException;
import com.example.whopper.domain.feedback.application.usecase.AddFeedbackUseCase;
import com.example.whopper.domain.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.domain.feedback.domain.FeedbackEntity;
import com.example.whopper.domain.feedback.dto.FeedbackRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddFeedbackService implements AddFeedbackUseCase {

    private final FeedbackMongoRepository feedbackMongoRepository;

    private final DocumentRepository documentRepository;

    public void addFeedback(FeedbackRequest request) {
        DocumentEntity document = documentRepository.findById(request.document_id())
                        .orElseThrow(()-> DocumentNotFoundException.EXCEPTION);

        feedbackMongoRepository.save(
                FeedbackEntity.builder()
                        .content(request.content())
                        .writerName(request.writer_name())
                        .elementId(request.element_id())
                        .document(document)
                        .build());
    }
}
