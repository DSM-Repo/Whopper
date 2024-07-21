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
        DocumentEntity document = documentRepository.findById(request.documentId())
                        .orElseThrow(()-> DocumentNotFoundException.EXCEPTION);

        feedbackMongoRepository.save(
                FeedbackEntity.builder()
                        .type(request.type())
                        .content(request.content())
                        .writerName(request.writerName())
                        .document(document)
                        .build());
    }
}
