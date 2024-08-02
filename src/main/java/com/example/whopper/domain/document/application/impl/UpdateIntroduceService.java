package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.base.AbstractUpdateElementServiceBase;
import com.example.whopper.domain.document.application.usecase.UpdateIntroduceUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.IntroduceElement;
import com.example.whopper.global.utils.current.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class UpdateIntroduceService extends AbstractUpdateElementServiceBase<IntroduceElement> implements UpdateIntroduceUseCase {
    public UpdateIntroduceService(DocumentRepository documentRepository, CurrentUser currentUser) {
        super(documentRepository, currentUser);
    }

    @Override
    protected void updateDocument(DocumentEntity document, IntroduceElement request) {
        document.updateIntroduceElement(request);
    }
}
