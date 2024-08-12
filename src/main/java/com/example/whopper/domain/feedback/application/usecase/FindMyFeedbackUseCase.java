package com.example.whopper.domain.feedback.application.usecase;

import com.example.whopper.domain.feedback.dto.FeedbackResponse;
import com.example.whopper.global.utils.DataResponseInfo;

public interface FindMyFeedbackUseCase {
    DataResponseInfo<FeedbackResponse> findMyFeedback();
}
