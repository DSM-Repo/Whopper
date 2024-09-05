package com.repo.whopper.application.resume.service;

import com.repo.whopper.application.resume.usecase.ResumeReviseUseCase;
import com.repo.whopper.application.student.component.CurrentStudent;
import com.repo.whopper.common.exception.major.MajorNotFoundException;
import com.repo.whopper.domain.major.MajorModel;
import com.repo.whopper.domain.major.MajorRepository;
import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.domain.resume.ResumeRepository;
import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;
import com.repo.whopper.interfaces.resume.dto.request.UpdateWriterInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ResumeReviseService implements ResumeReviseUseCase {
    private final CurrentStudent currentStudent;
    private final ResumeRepository resumeRepository;
    private final MajorRepository majorRepository;

    @Override
    public void revise(ResumeElementDto.ReviseRequest request) {
        final var resume = currentStudent.getResume();
        reviseProcess(request, resume);
    }

    private void reviseProcess(final ResumeElementDto.ReviseRequest request, final ResumeModel resume) {
        final var writerReq = request.writer();
        final var major = getMajor(writerReq.majorId());

        final var newResume = createNewModel(request, resume, major, writerReq);

        resumeRepository.save(newResume);
    }

    private ResumeModel createNewModel(final ResumeElementDto.ReviseRequest request, final ResumeModel resume, final MajorModel major, final UpdateWriterInfoRequest writerReq) {
        return resume.replace(
                resume.writer().update(new ResumeElementDto.Writer.Major(major.id(), major.name()), writerReq.email(), writerReq.skillSet(), writerReq.url()),
                request.projectList(),
                request.introduce(),
                request.achievementList(),
                request.activityList()
        );
    }

    private MajorModel getMajor(final String majorId) {
        return majorRepository.findById(majorId)
                .orElseThrow(() -> MajorNotFoundException.EXCEPTION);
    }
}
