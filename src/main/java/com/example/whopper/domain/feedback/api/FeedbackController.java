package com.example.whopper.domain.feedback.api;

import com.example.whopper.domain.feedback.application.usecase.AddFeedbackUseCase;
import com.example.whopper.domain.feedback.application.usecase.DeleteFeedbackUseCase;
import com.example.whopper.domain.feedback.application.usecase.FindFeedbackUseCase;
import com.example.whopper.domain.feedback.application.usecase.UpdateFeedbackUseCase;
import com.example.whopper.domain.feedback.dto.FeedbackRequest;
import com.example.whopper.domain.feedback.dto.FeedbackResponse;
import com.example.whopper.domain.feedback.dto.UpdateFeedbackRequest;
import com.example.whopper.global.annotation.OnlyStudent;
import com.example.whopper.global.annotation.OnlyTeacher;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final AddFeedbackUseCase addFeedbackUseCase;

    private final UpdateFeedbackUseCase updateFeedbackUseCase;

    private final DeleteFeedbackUseCase deleteFeedbackUseCase;

    private final FindFeedbackUseCase findFeedbackUseCase;

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
}
