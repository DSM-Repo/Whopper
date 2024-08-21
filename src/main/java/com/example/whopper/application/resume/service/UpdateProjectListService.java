package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.base.AbstractUpdateElementServiceBase;
import com.example.whopper.application.resume.usecase.UpdateProjectListUseCase;
import com.example.whopper.domain.resume.DocumentRepository;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.element.ProjectElement;
import com.example.whopper.interfaces.resume.dto.ProjectElementDto;
import com.example.whopper.global.utils.current.CurrentStudent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UpdateProjectListService extends AbstractUpdateElementServiceBase<List<ProjectElementDto>> implements UpdateProjectListUseCase {
    public UpdateProjectListService(DocumentRepository documentRepository, CurrentStudent currentUser) {
        super(documentRepository, currentUser);
    }

    @Override
    protected void updateDocument(DocumentEntity document, List<ProjectElementDto> list) {
        document.updateProjectList(list.stream()
                .map(ProjectElement::fromRequest)
                .toList()
        );
    }
}
