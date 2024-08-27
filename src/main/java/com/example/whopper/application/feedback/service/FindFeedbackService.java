package com.example.whopper.application.feedback.service;

import com.example.whopper.application.feedback.usecase.FindFeedbackUseCase;
import com.example.whopper.application.teacher.component.TeacherComponent;
import com.example.whopper.common.http.response.DataResponseInfo;
import com.example.whopper.application.student.component.CurrentStudent;
import com.example.whopper.domain.feedback.FeedbackModel;
import com.example.whopper.domain.feedback.FeedbackRepository;
import com.example.whopper.interfaces.feedback.dto.FeedbackElementDto;
import com.example.whopper.interfaces.feedback.dto.response.FeedbackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FindFeedbackService implements FindFeedbackUseCase {
    private final CurrentStudent currentStudent;
    private final FeedbackRepository feedbackRepository;
    private final TeacherComponent teacherComponent;

    @Override
    @Transactional(readOnly = true)
    public DataResponseInfo<FeedbackResponse.StudentResponse> getCurrentStudentFeedbackList() {
        final var resume = currentStudent.getResume();

        final var feedbackList = getFeedbackResponsesByResumeId(resume.id())
                .map(FeedbackResponse.StudentResponse::fromFeedback)
                .toList();

        return DataResponseInfo.of(feedbackList);
    }

    private Stream<FeedbackModel> getFeedbackResponsesByResumeId(String id) {
        return feedbackRepository.findAllByResumeIdAndStatus(id, FeedbackElementDto.Status.PENDING);
    }

    @Override
    @Transactional(readOnly = true)
    public DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbackListByResumeId(String resumeId) {
        final var currentTeacher = teacherComponent.currentTeacher();
        final var feedbackList =  feedbackRepository.findAllByResumeIdAndWriterId(resumeId, currentTeacher.id())
                .map(FeedbackResponse.TeacherResponse::fromFeedback)
                .toList();

        return DataResponseInfo.of(feedbackList);
    }

    @Override
    @Transactional(readOnly = true)
    public DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbacksWrittenByTeacher() {
        final var teacher = teacherComponent.currentTeacher();

        final var result = feedbackRepository.findAllByWriterId(teacher.id())
                .map(FeedbackResponse.TeacherResponse::fromFeedback)
                .toList();

        return DataResponseInfo.of(result);
    }
}
