package com.example.whopper.application.student.component;

import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.student.StudentEntity;

public interface CurrentStudent {
    StudentEntity getStudent();
    ResumeModel getDocument();
}
