package com.dsm.repo.internal.core.domain.model.resume;

import com.dsm.repo.internal.core.domain.model.student.StudentModel;
import com.dsm.repo.external.web.rest.resume.dto.ResumeElementDto;

import java.time.Year;

public class ResumeModelFactory {
    public static ResumeModel createResumeModelFromStudentEntity(StudentModel student) {
        var classInfo = student.classInfo();

        return ResumeModel.createInitialResume(
                student.accountId(),
                student.name(),
                new ResumeElementDto.Writer.SchoolInfo(
                        classInfo.grade(),
                        classInfo.classNumber(),
                        classInfo.number(),
                        classInfo.schoolNumber(),
                        Year.now().getValue() - 2013 - classInfo.grade()
                ),
                student.major()
        );
    }
}
