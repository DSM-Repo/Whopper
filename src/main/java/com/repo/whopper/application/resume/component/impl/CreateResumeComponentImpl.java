package com.repo.whopper.application.resume.component.impl;

import com.repo.whopper.application.resume.component.CreateResumeComponent;
import com.repo.whopper.domain.resume.ResumeRepository;
import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.domain.resume.ResumeModelFactory;
import com.repo.whopper.domain.student.StudentModel;
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
