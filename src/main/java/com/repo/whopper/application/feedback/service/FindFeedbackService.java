package com.repo.whopper.application.feedback.service;

import com.repo.whopper.application.feedback.usecase.FindFeedbackUseCase;
import com.repo.whopper.application.teacher.component.CurrentTeacher;
import com.repo.whopper.common.http.response.DataResponseInfo;
import com.repo.whopper.application.student.component.CurrentStudent;
import com.repo.whopper.domain.feedback.FeedbackModel;
import com.repo.whopper.domain.feedback.FeedbackRepository;
import com.repo.whopper.interfaces.feedback.dto.FeedbackElementDto;
import com.repo.whopper.interfaces.feedback.dto.response.FeedbackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FindFeedbackService implements FindFeedbackUseCase {
    private final CurrentStudent currentStudent;
    private final FeedbackRepository feedbackRepository;
    private final CurrentTeacher currentTeacher;

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
        final var teacher = currentTeacher.getTeacher();

        final var feedbackList =  feedbackRepository.findAllByResumeIdAndWriterId(resumeId, teacher.id())
                .map(FeedbackResponse.TeacherResponse::fromFeedback)
                .toList();

        return DataResponseInfo.of(feedbackList);
    }

    @Override
    @Transactional(readOnly = true)
    public DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbacksWrittenByTeacher() {
        final var teacher = currentTeacher.getTeacher();

        final var result = feedbackRepository.findAllByWriterId(teacher.id())
                .map(FeedbackResponse.TeacherResponse::fromFeedback)
                .toList();

        return DataResponseInfo.of(result);
    }
}