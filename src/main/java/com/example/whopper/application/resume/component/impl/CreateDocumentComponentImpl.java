package com.example.whopper.application.resume.component.impl;

import com.example.whopper.application.resume.component.CreateDocumentComponent;
import com.example.whopper.domain.resume.ResumeRepository;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.resume.ResumeModelFactory;
import com.example.whopper.domain.student.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CreateDocumentComponentImpl implements CreateDocumentComponent {
    private final ResumeRepository resumeRepository;

    @Override
    public ResumeModel create(StudentEntity student) {
        var document = ResumeModelFactory.createResumeModelFromStudentEntity(student);

        return resumeRepository.save(document);
    }
}
