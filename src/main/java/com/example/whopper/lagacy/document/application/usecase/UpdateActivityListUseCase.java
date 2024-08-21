package com.example.whopper.lagacy.document.application.usecase;

import com.example.whopper.lagacy.document.dto.ActivityElementDto;

import java.util.List;

public interface UpdateActivityListUseCase {
    void update(List<ActivityElementDto> request);
}
