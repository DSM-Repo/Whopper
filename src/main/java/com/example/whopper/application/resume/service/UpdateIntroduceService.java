package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.base.AbstractUpdateElementServiceBase;
import com.example.whopper.application.resume.usecase.UpdateIntroduceUseCase;
import com.example.whopper.domain.resume.DocumentRepository;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.element.IntroduceElement;
import com.example.whopper.interfaces.resume.dto.IntroduceElementDto;
import com.example.whopper.global.utils.current.CurrentStudent;
import org.springframework.stereotype.Service;

@Service
class UpdateIntroduceService extends AbstractUpdateElementServiceBase<IntroduceElementDto> implements UpdateIntroduceUseCase {
    public UpdateIntroduceService(DocumentRepository documentRepository, CurrentStudent currentUser) {
        super(documentRepository, currentUser);
    }

    @Override
    protected void updateDocument(DocumentEntity document, IntroduceElementDto request) {
        document.updateIntroduceElement(IntroduceElement.fromRequest(request));
    }
}
