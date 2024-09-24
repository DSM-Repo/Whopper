package com.repo.whopper.interfaces.feedback;

import com.repo.whopper.application.feedback.usecase.AcceptFeedbackUseCase;
import com.repo.whopper.application.feedback.usecase.AddFeedbackUseCase;
import com.repo.whopper.application.feedback.usecase.ConfirmFeedbackUseCase;
import com.repo.whopper.application.feedback.usecase.DeleteFeedbackUseCase;
import com.repo.whopper.application.feedback.usecase.FindFeedbackUseCase;
import com.repo.whopper.application.feedback.usecase.RejectFeedbackUseCase;
import com.repo.whopper.application.feedback.usecase.UpdateFeedbackUseCase;
import com.repo.whopper.interfaces.feedback.dto.request.FeedbackRequest;
import com.repo.whopper.interfaces.feedback.dto.response.FeedbackResponse;
import com.repo.whopper.interfaces.feedback.dto.request.UpdateFeedbackRequest;
import com.repo.whopper.common.annotation.OnlyStudent;
import com.repo.whopper.common.annotation.OnlyTeacher;
import com.repo.whopper.common.http.dto.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback")
class FeedbackController {
    private final AddFeedbackUseCase addFeedbackUseCase;
    private final AcceptFeedbackUseCase acceptFeedbackUseCase;
    private final RejectFeedbackUseCase rejectFeedbackUseCase;
    private final UpdateFeedbackUseCase updateFeedbackUseCase;
    private final DeleteFeedbackUseCase deleteFeedbackUseCase;
    private final FindFeedbackUseCase findFeedbackUseCase;
    private final ConfirmFeedbackUseCase confirmFeedbackUseCase;

    @OnlyStudent
    @PostMapping("/confirm")
    void confirm(@RequestParam String id) {
        confirmFeedbackUseCase.confirm(id);
    }

    @OnlyTeacher
    @PostMapping("/accept")
    void accept(@RequestParam String id) {
        acceptFeedbackUseCase.accept(id);
    }

    @OnlyTeacher
    @PostMapping("/reject")
    void reject(@RequestParam String id) {
        rejectFeedbackUseCase.reject(id);
    }

    @OnlyStudent
    @GetMapping
    DataResponseInfo<FeedbackResponse.StudentResponse> getMyFeedbackList() {
        return findFeedbackUseCase.getCurrentStudentFeedbackList();
    }

    @OnlyTeacher
    @GetMapping("/{resumeId}")
    DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbackListByResumeId(@PathVariable String resumeId) {
        return findFeedbackUseCase.getFeedbackListByResumeId(resumeId);
    }

    @OnlyTeacher
    @GetMapping("/teacher")
    DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbacksWrittenByTeacher() {
        return findFeedbackUseCase.getFeedbacksWrittenByTeacher();
    }

    @OnlyTeacher
    @PostMapping
    void addFeedback(@RequestBody FeedbackRequest request) {
        addFeedbackUseCase.addFeedback(request);
    }

    @OnlyTeacher
    @PatchMapping("/{feedbackId}")
    void updateFeedback(@PathVariable String feedbackId, @RequestBody UpdateFeedbackRequest request) {
        updateFeedbackUseCase.updateFeedback(feedbackId, request);
    }

    @OnlyTeacher
    @DeleteMapping("/{feedbackId}")
    void deleteFeedback(@PathVariable String feedbackId) {
        deleteFeedbackUseCase.deleteFeedback(feedbackId);
    }

}
