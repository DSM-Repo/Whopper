package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.base.AbstractUpdateElementServiceBase;
import com.example.whopper.domain.document.application.usecase.UpdateProjectListUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.ProjectElement;
import com.example.whopper.domain.document.dto.request.UpdateProjectElementRequest;
import com.example.whopper.global.utils.current.CurrentStudent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateProjectListService extends AbstractUpdateElementServiceBase<List<UpdateProjectElementRequest>> implements UpdateProjectListUseCase {
    public UpdateProjectListService(DocumentRepository documentRepository, CurrentStudent currentUser) {
        super(documentRepository, currentUser);
    }

    @Override
    protected void updateDocument(DocumentEntity document, List<UpdateProjectElementRequest> list) {
        document.updateProjectList(list.stream()
                .map(ProjectElement::fromRequest)
                .toList()
        );
    }
}
