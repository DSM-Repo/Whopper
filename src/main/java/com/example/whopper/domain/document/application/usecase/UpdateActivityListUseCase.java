package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.dto.ActivityElementDto;

import java.util.List;

public interface UpdateActivityListUseCase {
    void update(List<ActivityElementDto> request);
}
