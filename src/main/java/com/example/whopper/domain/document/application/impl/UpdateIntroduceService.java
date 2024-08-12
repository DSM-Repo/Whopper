package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.base.AbstractUpdateElementServiceBase;
import com.example.whopper.domain.document.application.usecase.UpdateIntroduceUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.IntroduceElement;
import com.example.whopper.domain.document.dto.IntroduceElementDto;
import com.example.whopper.global.utils.current.CurrentStudent;
import org.springframework.stereotype.Service;

@Service
public class UpdateIntroduceService extends AbstractUpdateElementServiceBase<IntroduceElementDto> implements UpdateIntroduceUseCase {
    public UpdateIntroduceService(DocumentRepository documentRepository, CurrentStudent currentUser) {
        super(documentRepository, currentUser);
    }

    @Override
    protected void updateDocument(DocumentEntity document, IntroduceElementDto request) {
        document.updateIntroduceElement(IntroduceElement.fromRequest(request));
    }
}
