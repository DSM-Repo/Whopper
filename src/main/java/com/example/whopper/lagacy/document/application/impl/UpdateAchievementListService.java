package com.example.whopper.lagacy.document.application.impl;

import com.example.whopper.lagacy.document.application.base.AbstractUpdateElementServiceBase;
import com.example.whopper.lagacy.document.application.usecase.UpdateAchievementListUseCase;
import com.example.whopper.lagacy.document.dao.DocumentRepository;
import com.example.whopper.lagacy.document.domain.DocumentEntity;
import com.example.whopper.lagacy.document.domain.element.AchievementElement;
import com.example.whopper.lagacy.document.dto.AchievementElementDto;
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
