package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.domain.element.ProjectElement;
import com.example.whopper.domain.document.dto.request.ProjectElementRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UpdateProjectListUseCase {
    void update(List<ProjectElementRequest> request, List<MultipartFile> images);
}
