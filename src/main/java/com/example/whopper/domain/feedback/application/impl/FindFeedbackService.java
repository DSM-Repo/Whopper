package com.example.whopper.domain.feedback.application.impl;

import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.exception.DocumentNotFoundException;
import com.example.whopper.domain.feedback.application.usecase.FindFeedbackUseCase;
import com.example.whopper.domain.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.domain.feedback.dto.FeedbackResponse;
import com.example.whopper.global.utils.DataResponseInfo;
import com.example.whopper.global.utils.current.CurrentStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindFeedbackService implements FindFeedbackUseCase {

    private final CurrentStudent currentStudent;
    private final FeedbackMongoRepository feedbackMongoRepository;
    private final DocumentRepository documentRepository;

    @Override
    public DataResponseInfo<FeedbackResponse> getCurrentStudentFeedbackList() {
        DocumentEntity document = currentStudent.getDocument();

        List<FeedbackResponse> feedbackList = getFeedbackResponsesByDocumentId(document.getId());

        return DataResponseInfo.of(feedbackList);
    }

    private List<FeedbackResponse> getFeedbackResponsesByDocumentId(String documentId) {
        return feedbackMongoRepository.findAllByDocumentId(documentId)
                .stream()
                .map(FeedbackResponse::new)
                .toList();
    }

    @Override
    public DataResponseInfo<FeedbackResponse> getFeedbackListByDocumentId(String documentId) {
        Boolean bool =  documentRepository.existsByDocumentId(documentId);

        if (!bool) {
            throw DocumentNotFoundException.EXCEPTION;
        }
        List<FeedbackResponse> feedbackList = getFeedbackResponsesByDocumentId(documentId);

        return DataResponseInfo.of(feedbackList);
    }
}
