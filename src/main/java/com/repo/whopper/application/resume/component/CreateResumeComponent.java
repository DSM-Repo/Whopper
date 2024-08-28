package com.repo.whopper.application.resume.component;

import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.domain.student.StudentModel;

public interface CreateResumeComponent {
    ResumeModel create(StudentModel student);
}
