package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.base.AbstractUpdateElementServiceBase;
import com.example.whopper.domain.document.application.usecase.UpdateAchievementListUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.AchievementElement;
import com.example.whopper.global.utils.current.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateAchievementListService extends AbstractUpdateElementServiceBase<List<AchievementElement>> implements UpdateAchievementListUseCase {
    public UpdateAchievementListService(DocumentRepository documentRepository, CurrentUser currentUser) {
        super(documentRepository, currentUser);
    }

    @Override
    protected void updateDocument(DocumentEntity document, List<AchievementElement> list) {
        document.updateArchievementList(list);
    }
}
