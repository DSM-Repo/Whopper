package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.base.AbstractUpdateElementServiceBase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.AwardElement;
import com.example.whopper.global.utils.current.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateAwardListService extends AbstractUpdateElementServiceBase<List<AwardElement>> {
    public UpdateAwardListService(DocumentRepository documentRepository, CurrentUser currentUser) {
        super(documentRepository, currentUser);
    }

    @Override
    protected void updateDocument(DocumentEntity document, List<AwardElement> list) {
        document.updateAwardList(list);
    }
}
