package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.usecase.UpdateProjectListUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import com.example.whopper.domain.document.domain.element.ProjectElement;
import com.example.whopper.domain.document.dto.request.ProjectElementRequest;
import com.example.whopper.domain.document.exception.DocumentModificationException;
import com.example.whopper.domain.document.exception.ImageRequestSizeMismatchException;
import com.example.whopper.global.utils.current.CurrentStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class UpdateProjectListService implements UpdateProjectListUseCase {
    private final DocumentRepository documentRepository;
    private final CurrentStudent currentStudent;

    private void updateDocument(DocumentEntity document, List<ProjectElement> list) {
        document.updateProjectList(list);
    }

    @Override
    public void update(List<ProjectElementRequest> request, List<MultipartFile> images) {
        if (images.size() != request.size()) {
            throw ImageRequestSizeMismatchException.EXCEPTION;
        }

        var document = currentStudent.getDocument();

        if (!document.getStatus().equals(DocumentStatus.ONGOING)) {
            throw DocumentModificationException.EXCEPTION;
        }

        List<String> imagePathList = images.stream()
                .map(img -> {
                    if (img.isEmpty()) {
                        return "";
                    }
                    return "image path"; // 저장 후 접근 가능한 URL
                }).toList(); // TODO: 7/27/24 이미지 저장에 대해 생각해야함
        // if (images.get() == null) => ""

        var projectElementList = IntStream.range(0, request.size())
                .mapToObj(i -> ProjectElement.fromProjectElementRequest(
                        request.get(i),
                        imagePathList.get(i))
                )
                .toList();

        updateDocument(document, projectElementList);
        documentRepository.save(document);
    }
}
