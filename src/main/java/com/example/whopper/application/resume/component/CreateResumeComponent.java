package com.example.whopper.application.resume.component;

import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.student.StudentModel;

public interface CreateResumeComponent {
    ResumeModel create(StudentModel student);
}
