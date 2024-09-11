package com.repo.whopper.application.resume.service;

import com.repo.whopper.application.resume.usecase.ResumeReviseUseCase;
import com.repo.whopper.application.student.component.CurrentStudent;
import com.repo.whopper.application.student.event.StudentMajorUpdateEvent;
import com.repo.whopper.common.exception.major.MajorNotFoundException;
import com.repo.whopper.domain.major.MajorModel;
import com.repo.whopper.domain.major.MajorRepository;
import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.domain.resume.ResumeRepository;
import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;
import com.repo.whopper.interfaces.resume.dto.request.UpdateWriterInfoRequest;
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
