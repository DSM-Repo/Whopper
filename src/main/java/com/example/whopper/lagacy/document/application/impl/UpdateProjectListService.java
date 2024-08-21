package com.example.whopper.lagacy.document.application.impl;

import com.example.whopper.lagacy.document.application.base.AbstractUpdateElementServiceBase;
import com.example.whopper.lagacy.document.application.usecase.UpdateProjectListUseCase;
import com.example.whopper.lagacy.document.dao.DocumentRepository;
import com.example.whopper.lagacy.document.domain.DocumentEntity;
import com.example.whopper.lagacy.document.domain.element.ProjectElement;
import com.example.whopper.lagacy.document.dto.ProjectElementDto;
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
