package com.dsm.repo.internal.core.domain.component.student;

import com.dsm.repo.internal.core.domain.model.resume.ResumeModel;
import com.dsm.repo.internal.core.domain.model.student.StudentModel;

public interface CurrentStudent {
    StudentModel getStudent();
    ResumeModel getResume();
    ResumeModel getResume(String studentId);
}
