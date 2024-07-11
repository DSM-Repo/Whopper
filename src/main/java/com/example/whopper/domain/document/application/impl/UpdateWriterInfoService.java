package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.base.AbstractUpdateElementServiceBase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.WriterInfoElement;
import com.example.whopper.domain.document.dto.request.UpdateWriterInfoRequest;
import com.example.whopper.global.utils.current.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class UpdateWriterInfoService extends AbstractUpdateElementServiceBase<UpdateWriterInfoRequest> {
    public UpdateWriterInfoService(DocumentRepository documentRepository, CurrentUser currentUser) {
        super(documentRepository, currentUser);
    }

    @Override
    protected void updateDocument(DocumentEntity document, UpdateWriterInfoRequest request) {
        WriterInfoElement updatedWriterInfo = updateWriterInfo(document.getWriter(), request);
        document.updateWriterInfo(updatedWriterInfo);
    }

    private WriterInfoElement updateWriterInfo(WriterInfoElement writerInfo, UpdateWriterInfoRequest request) {
        return WriterInfoElement.builder()
                .email(request.email())
                .profileImagePath(writerInfo.profileImagePath())
                .major(request.major())
                .skillSet(request.skillSet())
                .build();
    }
}
