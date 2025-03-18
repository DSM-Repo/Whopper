package com.dsm.repo.external.web.rest.feedback;

import com.dsm.repo.internal.core.usecase.feedback.AcceptFeedbackUseCase;
import com.dsm.repo.internal.core.usecase.feedback.AddFeedbackUseCase;
import com.dsm.repo.internal.core.usecase.feedback.ConfirmFeedbackUseCase;
import com.dsm.repo.internal.core.usecase.feedback.DeleteFeedbackUseCase;
import com.dsm.repo.internal.core.usecase.feedback.FindFeedbackUseCase;
import com.dsm.repo.internal.core.usecase.feedback.RejectFeedbackUseCase;
import com.dsm.repo.internal.core.usecase.feedback.UpdateFeedbackUseCase;
import com.dsm.repo.external.web.rest.feedback.dto.request.FeedbackRequest;
import com.dsm.repo.external.web.rest.feedback.dto.response.FeedbackResponse;
import com.dsm.repo.external.web.rest.feedback.dto.request.UpdateFeedbackRequest;
import com.dsm.repo.external.security.auth.annotation.OnlyStudent;
import com.dsm.repo.external.security.auth.annotation.OnlyTeacher;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback")
class FeedbackController { // TODO: 3/18/25 SRP 적용해야함

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
    void updateFeedback(
            @PathVariable String feedbackId,
            @RequestBody UpdateFeedbackRequest request
    ) {
        updateFeedbackUseCase.updateFeedback(feedbackId, request);
    }

    @OnlyTeacher
    @DeleteMapping("/{feedbackId}")
    void deleteFeedback(@PathVariable String feedbackId) {
        deleteFeedbackUseCase.deleteFeedback(feedbackId);
    }

}
