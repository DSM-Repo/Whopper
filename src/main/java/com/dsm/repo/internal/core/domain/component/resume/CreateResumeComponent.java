package com.dsm.repo.internal.core.domain.component.resume;

import com.dsm.repo.internal.core.domain.model.resume.ResumeModel;
import com.dsm.repo.internal.core.domain.model.student.StudentModel;

public interface CreateResumeComponent {
    ResumeModel create(StudentModel student);
}
