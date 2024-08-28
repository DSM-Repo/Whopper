package com.repo.whopper.application.student.component;

import com.repo.whopper.domain.resume.ResumeModel;
import com.repo.whopper.domain.student.StudentModel;

public interface CurrentStudent {
    StudentModel getStudent();
    ResumeModel getResume();
}
