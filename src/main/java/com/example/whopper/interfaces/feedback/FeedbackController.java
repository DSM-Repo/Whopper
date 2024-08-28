package com.example.whopper.interfaces.feedback;

import com.example.whopper.application.feedback.usecase.AcceptFeedbackUseCase;
import com.example.whopper.application.feedback.usecase.AddFeedbackUseCase;
import com.example.whopper.application.feedback.usecase.ConfirmFeedbackUseCase;
import com.example.whopper.application.feedback.usecase.DeleteFeedbackUseCase;
import com.example.whopper.application.feedback.usecase.FindFeedbackUseCase;
import com.example.whopper.application.feedback.usecase.RejectFeedbackUseCase;
import com.example.whopper.application.feedback.usecase.UpdateFeedbackUseCase;
import com.example.whopper.interfaces.feedback.dto.request.FeedbackRequest;
import com.example.whopper.interfaces.feedback.dto.response.FeedbackResponse;
import com.example.whopper.interfaces.feedback.dto.request.UpdateFeedbackRequest;
import com.example.whopper.common.annotation.OnlyStudent;
import com.example.whopper.common.annotation.OnlyTeacher;
import com.example.whopper.common.http.response.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {
    private final AddFeedbackUseCase addFeedbackUseCase;
    private final AcceptFeedbackUseCase acceptFeedbackUseCase;
    private final RejectFeedbackUseCase rejectFeedbackUseCase;
    private final UpdateFeedbackUseCase updateFeedbackUseCase;
    private final DeleteFeedbackUseCase deleteFeedbackUseCase;
    private final FindFeedbackUseCase findFeedbackUseCase;
    private final ConfirmFeedbackUseCase confirmFeedbackUseCase;

    @OnlyStudent
    @PostMapping("/confirm")
    public void confirm(@RequestBody IdRequest request) {
        confirmFeedbackUseCase.confirm(request.id);
    }

    @OnlyTeacher
    @PostMapping("/accept")
    public void accept(@RequestBody IdRequest request) {
        acceptFeedbackUseCase.accept(request.id);
    }

    @OnlyTeacher
    @PostMapping("/reject")
    public void reject(@RequestBody IdRequest request) {
        rejectFeedbackUseCase.reject(request.id);
    }

    @OnlyStudent
    @GetMapping
    public DataResponseInfo<FeedbackResponse.StudentResponse> getMyFeedbackList() {
        return findFeedbackUseCase.getCurrentStudentFeedbackList();
    }

    @OnlyTeacher
    @GetMapping("/{resumeId}")
    public DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbackListByResumeId(@PathVariable String resumeId) {
        return findFeedbackUseCase.getFeedbackListByResumeId(resumeId);
    }

    @OnlyTeacher
    @GetMapping("/teacher")
    public DataResponseInfo<FeedbackResponse.TeacherResponse> getFeedbacksWrittenByTeacher() {
        return findFeedbackUseCase.getFeedbacksWrittenByTeacher();
    }

    @OnlyTeacher
    @PostMapping
    public void addFeedback(@RequestBody FeedbackRequest request) {
        addFeedbackUseCase.addFeedback(request);
    }

    @OnlyTeacher
    @PatchMapping("/{feedbackId}")
    public void updateFeedback(@PathVariable String feedbackId, @RequestBody UpdateFeedbackRequest request) {
        updateFeedbackUseCase.updateFeedback(feedbackId, request);
    }

    @OnlyTeacher
    @PutMapping("/del")
    public void deleteFeedback(@RequestBody IdRequest request) {
        deleteFeedbackUseCase.deleteFeedback(request.id());
    }

    record IdRequest(String id) {}
}
