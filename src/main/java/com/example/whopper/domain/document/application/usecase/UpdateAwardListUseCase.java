package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.domain.element.AwardElement;

import java.util.List;

public interface UpdateAwardListUseCase {
    void update(List<AwardElement> request);
}
