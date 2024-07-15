package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.base.AbstractUpdateElementServiceBase;
import com.example.whopper.domain.document.application.usecase.UpdateProjectListUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.ProjectElement;
import com.example.whopper.global.utils.current.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateProjectListService extends AbstractUpdateElementServiceBase<List<ProjectElement>> implements UpdateProjectListUseCase {
    public UpdateProjectListService(DocumentRepository documentRepository, CurrentUser currentUser) {
        super(documentRepository, currentUser);
    }

    @Override
    protected void updateDocument(DocumentEntity document, List<ProjectElement> list) {
        document.updateProjectList(list);
    }
}
