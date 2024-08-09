package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.domain.element.IntroduceElement;
import com.example.whopper.domain.document.dto.request.UpdateIntroduceElementRequest;

public interface UpdateIntroduceUseCase {
    void update(UpdateIntroduceElementRequest request);
}
