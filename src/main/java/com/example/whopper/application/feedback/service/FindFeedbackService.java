package com.example.whopper.application.feedback.service;

import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.exception.DocumentNotFoundException;
import com.example.whopper.domain.feedback.application.usecase.FindFeedbackUseCase;
import com.example.whopper.domain.feedback.dao.FeedbackMongoRepository;
import com.example.whopper.domain.feedback.dto.FeedbackResponse;
import com.example.whopper.domain.teacher.application.component.TeacherComponent;
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
    private final TeacherComponent teacherComponent;

    @Override
    public DataResponseInfo<FeedbackResponse.StudentResponse> getCurrentStudentFeedbackList() {
        DocumentEntity document = currentStudent.getDocument();

        List<FeedbackResponse.StudentResponse> feedbackList = getFeedbackResponsesByDocumentId(document.getId())
                .stream()
                .map(FeedbackResponse.StudentResponse::fromFeedback)
                .toList();

        return DataResponseInfo.of(feedbackList);
    }

    private List<FeedbackEntity> getFeedbackResponsesByDocumentId(String documentId) {
        return feedbackMongoRepository.findAllByDocumentId(documentId);
    }

    @Override
    public DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbackListByDocumentId(String documentId) {
        final var currentTeacher = teacherComponent.currentTeacher();
        final var feedbackList =  feedbackMongoRepository.findAllByDocumentIdAndTeacherId(documentId, currentTeacher.getId())
                .map(FeedbackResponse.TeacherResponse::fromFeedback)
                .toList();

        return DataResponseInfo.of(feedbackList);
    }

    @Override
    public DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbacksWrittenByTeacher() {
        var teacher = teacherComponent.currentTeacher();

        var result = feedbackMongoRepository.findAllByTeacherId(teacher.getId())
                .map(FeedbackResponse.TeacherResponse::fromFeedback)
                .toList();

        return DataResponseInfo.of(result);
    }
}
