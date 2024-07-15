package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.application.base.UpdateElementUseCaseBase;
import com.example.whopper.domain.document.domain.element.ActivityElement;

import java.util.List;

public interface UpdateActivityListUseCase extends UpdateElementUseCaseBase<List<ActivityElement>> {
    void update(List<ActivityElement> request);
}
