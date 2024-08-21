package com.example.whopper.interfaces.feedback;

import com.example.whopper.application.feedback.usecase.AddFeedbackUseCase;
import com.example.whopper.application.feedback.usecase.ConfirmFeedbackUseCase;
import com.example.whopper.application.feedback.usecase.DeleteFeedbackUseCase;
import com.example.whopper.application.feedback.usecase.FindFeedbackUseCase;
import com.example.whopper.application.feedback.usecase.UpdateFeedbackUseCase;
import com.example.whopper.lagacy.feedback.application.usecase.*;
import com.example.whopper.interfaces.feedback.dto.FeedbackRequest;
import com.example.whopper.interfaces.feedback.dto.FeedbackResponse;
import com.example.whopper.interfaces.feedback.dto.UpdateFeedbackRequest;
import com.example.whopper.common.annotation.OnlyStudent;
import com.example.whopper.common.annotation.OnlyTeacher;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {

    private final AddFeedbackUseCase addFeedbackUseCase;

    private final UpdateFeedbackUseCase updateFeedbackUseCase;

    private final DeleteFeedbackUseCase deleteFeedbackUseCase;

    private final FindFeedbackUseCase findFeedbackUseCase;

    private final ConfirmFeedbackUseCase confirmFeedbackUseCase;

    @OnlyStudent
    @PostMapping("/confirm")
    public void confirm(@RequestBody ConfirmRequest request) {
        confirmFeedbackUseCase.confirm(request.id);
    }

    @OnlyStudent
    @GetMapping
    public DataResponseInfo<FeedbackResponse> getMyFeedbackList() {
        return findFeedbackUseCase.getCurrentStudentFeedbackList();
    }

    @OnlyTeacher
    @GetMapping("/{documentId}")
    public DataResponseInfo<FeedbackResponse> getFeedbackListByDocumentId(@PathVariable String documentId) {
        return findFeedbackUseCase.getFeedbackListByDocumentId(documentId);
    }

    @OnlyTeacher
    @GetMapping("/teacher")
    public DataResponseInfo<FeedbackResponse> getFeedbacksWrittenByTeacher() {
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
    @DeleteMapping("/{feedbackId}")
    public void deleteFeedback(@PathVariable String feedbackId) {
        deleteFeedbackUseCase.deleteFeedback(feedbackId);
    }

    record ConfirmRequest(String id) {}
}
