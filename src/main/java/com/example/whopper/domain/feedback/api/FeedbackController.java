package com.example.whopper.domain.feedback.api;

import com.example.whopper.domain.feedback.application.usecase.AddFeedbackUseCase;
import com.example.whopper.domain.feedback.application.usecase.DeleteFeedbackUseCase;
import com.example.whopper.domain.feedback.application.usecase.UpdateFeedbackUseCase;
import com.example.whopper.domain.feedback.dto.FeedbackRequest;
import com.example.whopper.domain.feedback.dto.UpdateFeedbackRequest;
import com.example.whopper.global.annotation.OnlyTeacher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final AddFeedbackUseCase addFeedbackUseCase;

    private final UpdateFeedbackUseCase updateFeedbackUseCase;

    private final DeleteFeedbackUseCase deleteFeedbackUseCase;

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
