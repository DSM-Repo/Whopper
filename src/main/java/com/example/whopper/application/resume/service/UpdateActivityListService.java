package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.base.AbstractUpdateElementServiceBase;
import com.example.whopper.application.resume.usecase.UpdateActivityListUseCase;
import com.example.whopper.domain.resume.DocumentRepository;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.element.ActivityElement;
import com.example.whopper.interfaces.resume.dto.ActivityElementDto;
import com.example.whopper.global.utils.current.CurrentStudent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UpdateActivityListService extends AbstractUpdateElementServiceBase<List<ActivityElementDto>> implements UpdateActivityListUseCase {
    public UpdateActivityListService(DocumentRepository documentRepository, CurrentStudent currentUser) {
        super(documentRepository, currentUser);
    }

    @Override
    protected void updateDocument(DocumentEntity document, List<ActivityElementDto> list) {
        document.updateActivityElement(list.stream()
                .map(ActivityElement::fromRequest)
                .toList()
        );
    }
}
