package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.base.AbstractUpdateElementServiceBase;
import com.example.whopper.application.resume.usecase.UpdateAchievementListUseCase;
import com.example.whopper.domain.resume.DocumentRepository;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.element.AchievementElement;
import com.example.whopper.interfaces.resume.dto.AchievementElementDto;
import com.example.whopper.application.student.component.CurrentStudent;
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
