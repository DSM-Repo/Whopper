package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.base.AbstractUpdateElementServiceBase;
import com.example.whopper.application.resume.usecase.UpdateWriterInfoUseCase;
import com.example.whopper.domain.resume.DocumentRepository;
import com.example.whopper.domain.resume.DocumentEntity;
import com.example.whopper.domain.resume.element.WriterInfoElement;
import com.example.whopper.interfaces.resume.dto.request.UpdateWriterInfoRequest;
import com.example.whopper.domain.major.MajorRepository;
import com.example.whopper.domain.major.DefaultMajorFacade;
import com.example.whopper.domain.student.StudentMongoRepository;
import com.example.whopper.global.utils.current.CurrentStudent;
import org.springframework.stereotype.Service;

@Service
class UpdateWriterInfoService extends AbstractUpdateElementServiceBase<UpdateWriterInfoRequest> implements UpdateWriterInfoUseCase {
    private final StudentMongoRepository studentMongoRepository;
    private final MajorRepository majorRepository;
    private final DefaultMajorFacade defaultMajorFacade;

    public UpdateWriterInfoService(DocumentRepository documentRepository, CurrentStudent currentUser, StudentMongoRepository studentMongoRepository, MajorRepository majorRepository, DefaultMajorFacade defaultMajorFacade) {
        super(documentRepository, currentUser);
        this.studentMongoRepository = studentMongoRepository;
        this.majorRepository = majorRepository;
        this.defaultMajorFacade = defaultMajorFacade;
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
                request.url()
        );
    }

    private void updateStudentEntityMajor(String majorId) {
        var user = currentStudent.getStudent();
        var major = majorRepository.findById(majorId)
                .orElseGet(defaultMajorFacade::getDefaultMajor);

        user.updateMajor(major);
        studentMongoRepository.save(user);
    }
}
