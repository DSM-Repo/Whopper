package com.example.whopper.application.resume.service;

import com.example.whopper.application.resume.base.AbstractUpdateElementServiceBase;
import com.example.whopper.application.resume.usecase.UpdateWriterInfoUseCase;
import com.example.whopper.application.student.event.StudentMajorUpdateEvent;
import com.example.whopper.common.exception.major.MajorNotFoundException;
import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;
import com.example.whopper.interfaces.resume.dto.request.UpdateWriterInfoRequest;
import com.example.whopper.domain.major.MajorRepository;
import com.example.whopper.application.student.component.CurrentStudent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
class UpdateWriterInfoService extends AbstractUpdateElementServiceBase<UpdateWriterInfoRequest> implements UpdateWriterInfoUseCase {
    private final MajorRepository majorRepository;
    private final ApplicationEventPublisher eventPublisher;

    public UpdateWriterInfoService(ResumeRepository resumeRepository, CurrentStudent currentUser, MajorRepository majorRepository, ApplicationEventPublisher eventPublisher) {
        super(resumeRepository, currentUser);
        this.majorRepository = majorRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    protected ResumeModel updateResume(ResumeModel resume, UpdateWriterInfoRequest request) {
        final var major = majorRepository.findById(request.majorId())
                .orElseThrow(() -> MajorNotFoundException.EXCEPTION);

        final var newWriter = resume.writer().update(new ResumeElementDto.Writer.Major(major.id(), major.name()), request.email(), request.skillSet(), request.url());

        eventPublisher.publishEvent(new StudentMajorUpdateEvent(major.id(), major.name()));

        return resume.updateWriterInfo(newWriter);
    }
}
