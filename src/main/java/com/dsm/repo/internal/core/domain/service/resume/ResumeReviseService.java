package com.dsm.repo.internal.core.domain.service.resume;

import com.dsm.repo.internal.core.usecase.resume.ResumeReviseUseCase;
import com.dsm.repo.internal.core.domain.component.student.CurrentStudent;
import com.dsm.repo.internal.core.domain.event.student.StudentMajorUpdateEvent;
import com.dsm.repo.external.exception.domain.major.MajorNotFoundException;
import com.dsm.repo.internal.core.domain.model.major.MajorModel;
import com.dsm.repo.internal.data.repository.major.MajorRepository;
import com.dsm.repo.internal.core.domain.model.resume.ResumeModel;
import com.dsm.repo.internal.data.repository.resume.ResumeRepository;
import com.dsm.repo.external.web.rest.resume.dto.ResumeElementDto;
import com.dsm.repo.external.web.rest.resume.dto.request.UpdateWriterInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ResumeReviseService implements ResumeReviseUseCase {
    private final CurrentStudent currentStudent;
    private final ResumeRepository resumeRepository;
    private final MajorRepository majorRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    public void revise(final ResumeElementDto.ReviseRequest request) {
        final var student = currentStudent.getStudent();
        final var resume = currentStudent.getResume(student.id());

        reviseProcess(request, resume);
        publishMajorUpdateEvent(request.writer().major(), student.id());
    }

    private void reviseProcess(final ResumeElementDto.ReviseRequest request, final ResumeModel resume) {
        final var writerReq = request.writer();
        final var major = getMajor(writerReq.major());

        final var newResume = createNewModel(request, resume, major, writerReq);

        resumeRepository.save(newResume);
    }

    private ResumeModel createNewModel(final ResumeElementDto.ReviseRequest request, final ResumeModel resume, final MajorModel major, final UpdateWriterInfoRequest writerReq) {
        return resume.replace(
                resume.writer().update(major.id(), writerReq.email(), writerReq.skillSet(), writerReq.url()),
                request.projectList(),
                request.introduce(),
                request.achievementList(),
                request.activityList()
        );
    }

    private void publishMajorUpdateEvent(final String majorName, final String userId) {
        publisher.publishEvent(new StudentMajorUpdateEvent(majorName, userId));
    }

    private MajorModel getMajor(final String majorName) {
        return majorRepository.findByName(majorName)
                .orElseThrow(() -> MajorNotFoundException.EXCEPTION);
    }
}
