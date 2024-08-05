package com.example.whopper.domain.feedback.api;

import com.example.whopper.domain.feedback.application.usecase.AddFeedbackUseCase;
import com.example.whopper.domain.feedback.application.usecase.DeleteFeedbackUseCase;
import com.example.whopper.domain.feedback.application.usecase.UpdateFeedbackUseCase;
import com.example.whopper.domain.feedback.dto.DeleteFeedbackRequest;
import com.example.whopper.domain.feedback.dto.FeedbackRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final AddFeedbackUseCase addFeedbackUseCase;

    private final UpdateFeedbackUseCase updateFeedbackUseCase;

    private final DeleteFeedbackUseCase deleteFeedbackUseCase;

    @PostMapping
    public void addFeedback(FeedbackRequest request) {
        addFeedbackUseCase.addFeedback(request);
    }

    @PatchMapping
    public void updateFeedback(FeedbackRequest request) {
        updateFeedbackUseCase.updateFeedback(request);
    }

    @DeleteMapping
    public void deleteFeedback(DeleteFeedbackRequest request) {
        deleteFeedbackUseCase.deleteFeedback(request);
    }
}
