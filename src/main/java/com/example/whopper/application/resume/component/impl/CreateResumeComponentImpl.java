package com.example.whopper.application.resume.component.impl;

import com.example.whopper.application.resume.component.CreateResumeComponent;
import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.resume.ResumeModelFactory;
import com.example.whopper.domain.student.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CreateResumeComponentImpl implements CreateResumeComponent {
    private final ResumeRepository resumeRepository;

    @Override
    public ResumeModel create(StudentEntity student) {
        var resume = ResumeModelFactory.createResumeModelFromStudentEntity(student);

        return resumeRepository.save(resume);
    }
}
