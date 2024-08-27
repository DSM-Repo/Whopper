package com.example.whopper.application.student.component;

import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.domain.student.StudentModel;

public interface CurrentStudent {
    StudentModel getStudent();
    ResumeModel getResume();
}
