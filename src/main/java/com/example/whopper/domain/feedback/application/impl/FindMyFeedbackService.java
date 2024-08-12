package com.example.whopper.domain.feedback.application.impl;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.feedback.application.usecase.FindMyFeedbackUseCase;
import com.example.whopper.domain.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.domain.feedback.dto.FeedbackResponse;
import com.example.whopper.domain.feedback.dto.MyFeedbackResponse;
import com.example.whopper.global.utils.current.CurrentStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FindMyFeedbackService implements FindMyFeedbackUseCase {

    private final CurrentStudent currentStudent;
    private final FeedbackMongoRepository feedbackMongoRepository;

    @Override
    public MyFeedbackResponse findMyFeedback() {
        DocumentEntity document = currentStudent.getDocument();
        Map<String, String> elementNameMap = document.getElementNameMap();

        List<FeedbackResponse> feedbackList = feedbackMongoRepository.findAllByDocumentId(document.getId())
                .stream()
                .map(feedback -> new FeedbackResponse(feedback, elementNameMap.get(feedback.getElementId())))
                .toList();

        return new MyFeedbackResponse(document.getId(), feedbackList);
    }
}
