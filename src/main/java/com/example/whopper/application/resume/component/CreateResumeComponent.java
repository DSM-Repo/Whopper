package com.example.whopper.application.resume.component;

import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.student.StudentEntity;

public interface CreateResumeComponent {
    ResumeModel create(StudentEntity student);
}
