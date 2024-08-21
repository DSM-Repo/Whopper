package com.example.whopper.application.resume.usecase;

import com.example.whopper.interfaces.resume.dto.ActivityElementDto;

import java.util.List;

public interface UpdateActivityListUseCase {
    void update(List<ActivityElementDto> request);
}
