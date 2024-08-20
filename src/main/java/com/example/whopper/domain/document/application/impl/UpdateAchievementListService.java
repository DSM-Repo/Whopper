package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.base.AbstractUpdateElementServiceBase;
import com.example.whopper.domain.document.application.usecase.UpdateAchievementListUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.AchievementElement;
import com.example.whopper.domain.document.dto.AchievementElementDto;
import com.example.whopper.global.utils.current.CurrentStudent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UpdateAchievementListService extends AbstractUpdateElementServiceBase<List<AchievementElementDto>> implements UpdateAchievementListUseCase {
    public UpdateAchievementListService(DocumentRepository documentRepository, CurrentStudent currentUser) {
        super(documentRepository, currentUser);
    }

    @Override
    protected void updateDocument(DocumentEntity document, List<AchievementElementDto> list) {
        document.updateAchievementList(list.stream()
                .map(AchievementElement::fromRequest)
                .toList()
        );
    }
}
