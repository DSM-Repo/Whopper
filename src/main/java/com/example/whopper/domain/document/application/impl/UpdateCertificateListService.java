package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.base.AbstractUpdateElementServiceBase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.CertificateElement;
import com.example.whopper.global.utils.current.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateCertificateListService extends AbstractUpdateElementServiceBase<List<CertificateElement>> {
    public UpdateCertificateListService(DocumentRepository documentRepository, CurrentUser currentUser) {
        super(documentRepository, currentUser);
    }

    @Override
    protected void updateDocument(DocumentEntity document, List<CertificateElement> list) {
        document.updateCertificateElement(list);
    }
}
