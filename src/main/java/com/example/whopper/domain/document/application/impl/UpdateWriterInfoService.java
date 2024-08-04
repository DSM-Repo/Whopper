package com.example.whopper.domain.document.application.impl;

import com.example.whopper.domain.document.application.base.AbstractUpdateElementServiceBase;
import com.example.whopper.domain.document.application.usecase.UpdateWriterInfoUseCase;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.WriterInfoElement;
import com.example.whopper.domain.document.dto.request.UpdateWriterInfoRequest;
import com.example.whopper.domain.student.dao.StudentMongoRepository;
import com.example.whopper.global.utils.current.CurrentStudent;
import org.springframework.stereotype.Service;

@Service
public class UpdateWriterInfoService extends AbstractUpdateElementServiceBase<UpdateWriterInfoRequest> implements UpdateWriterInfoUseCase {
    private final StudentMongoRepository studentMongoRepository;

    public UpdateWriterInfoService(DocumentRepository documentRepository, CurrentStudent currentUser, StudentMongoRepository studentMongoRepository) {
        super(documentRepository, currentUser);
        this.studentMongoRepository = studentMongoRepository;
    }

    @Override
    protected void updateDocument(DocumentEntity document, UpdateWriterInfoRequest request) {
        WriterInfoElement updatedWriterInfo = updateWriterInfo(document.getWriter(), request);
        document.updateWriterInfo(updatedWriterInfo);
        updateStudentEntityMajor(request.majorId());
    }

    private WriterInfoElement updateWriterInfo(WriterInfoElement writerInfo, UpdateWriterInfoRequest request) {
        return new WriterInfoElement(
                writerInfo.getElementId(),
                writerInfo.getGeneration(),
                request.email(),
                writerInfo.getProfileImagePath(),
                request.skillSet(),
                writerInfo.getUrl()
        );
    }

    private void updateStudentEntityMajor(String majorId) {
        var user = currentStudent.getStudent();
        user.updateMajor(majorId);

        studentMongoRepository.save(user);
    }
}
