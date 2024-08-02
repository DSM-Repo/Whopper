package com.example.whopper.domain.feedback.application.impl;

import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.AchievementElement;
import com.example.whopper.domain.document.domain.element.ActivityElement;
import com.example.whopper.domain.document.domain.element.ProjectElement;
import com.example.whopper.domain.document.exception.DocumentNotFoundException;
import com.example.whopper.domain.feedback.application.usecase.FindMyFeedbackUseCase;
import com.example.whopper.domain.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.domain.feedback.domain.FeedbackEntity;
import com.example.whopper.domain.feedback.dto.FeedbackResponse;
import com.example.whopper.domain.feedback.dto.MyFeedbackResponse;
import com.example.whopper.domain.student.application.component.usecase.CurrentStudentComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FindMyFeedbackService implements FindMyFeedbackUseCase {

    private final DocumentRepository documentRepository;
    private final CurrentStudentComponent currentStudentComponent;
    private final FeedbackMongoRepository feedbackMongoRepository;

    public DocumentEntity findDocumentByWriterName(String writerName) {
        return documentRepository.findByWriterId(currentStudentComponent.currentStudent().getId())
                .orElseThrow(() -> DocumentNotFoundException.EXCEPTION);
    }

    public String findElementName(FeedbackEntity feedback, DocumentEntity document) {
        Map<String, String> combinedMap = Stream.of(
                        document.getAchievementList().stream()
                                .collect(Collectors.toMap(AchievementElement::elementId, AchievementElement::name)),
                        document.getActivityList().stream()
                                .collect(Collectors.toMap(ActivityElement::elementId, ActivityElement::name)),
                        document.getProjectList().stream()
                                .collect(Collectors.toMap(ProjectElement::elementId, ProjectElement::name))
                ).flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (existing, replacement) -> existing));

        return combinedMap.get(feedback.getElementId());
    }

    public MyFeedbackResponse findMyFeedback() {

        DocumentEntity myDocument = findDocumentByWriterName(currentStudentComponent.currentStudent().getId());

        List<FeedbackResponse> feedbackList = feedbackMongoRepository.findAllByDocument(myDocument)
                .stream()
                .map(feedback -> new FeedbackResponse(feedback, findElementName(feedback, myDocument)))
                .toList();

        return new MyFeedbackResponse(myDocument.getId(), feedbackList);
    }
}
