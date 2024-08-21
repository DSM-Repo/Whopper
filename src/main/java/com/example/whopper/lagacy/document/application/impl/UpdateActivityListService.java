package com.example.whopper.lagacy.document.application.impl;

import com.example.whopper.lagacy.document.application.base.AbstractUpdateElementServiceBase;
import com.example.whopper.lagacy.document.application.usecase.UpdateActivityListUseCase;
import com.example.whopper.lagacy.document.dao.DocumentRepository;
import com.example.whopper.lagacy.document.domain.DocumentEntity;
import com.example.whopper.lagacy.document.domain.element.ActivityElement;
import com.example.whopper.lagacy.document.dto.ActivityElementDto;
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
