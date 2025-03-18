package com.dsm.repo.internal.core.domain.component.resume.impl;

import com.dsm.repo.internal.core.domain.component.resume.CreateResumeComponent;
import com.dsm.repo.internal.data.repository.resume.ResumeRepository;
import com.dsm.repo.internal.core.domain.model.resume.ResumeModel;
import com.dsm.repo.internal.core.domain.model.resume.ResumeModelFactory;
import com.dsm.repo.internal.core.domain.model.student.StudentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
class CreateResumeComponentImpl implements CreateResumeComponent {
    private final ResumeRepository resumeRepository;

    @Override
    @Transactional
    public ResumeModel create(StudentModel student) {
        final var resume = ResumeModelFactory.createResumeModelFromStudentEntity(student);

        return resumeRepository.save(resume);
    }
}
