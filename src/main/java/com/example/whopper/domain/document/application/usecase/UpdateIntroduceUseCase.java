package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.application.base.UpdateElementUseCaseBase;
import com.example.whopper.domain.document.domain.element.IntroduceElement;

public interface UpdateIntroduceUseCase extends UpdateElementUseCaseBase<IntroduceElement> {
    void update(IntroduceElement request);
}
