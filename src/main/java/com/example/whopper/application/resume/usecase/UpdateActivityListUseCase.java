package com.example.whopper.application.resume.usecase;

import com.example.whopper.interfaces.resume.dto.ResumeElementDto;

import java.util.List;

public interface UpdateActivityListUseCase {
    void update(List<ResumeElementDto.Activity> request);
}
