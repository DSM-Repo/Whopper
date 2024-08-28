package com.repo.whopper.domain.resume;

import com.repo.whopper.domain.student.StudentModel;
import com.repo.whopper.interfaces.resume.dto.ResumeElementDto;

import java.time.Year;

public class ResumeModelFactory {
    public static ResumeModel createResumeModelFromStudentEntity(StudentModel student) {
        var classInfo = student.classInfo();
        final var major = student.major();

        return ResumeModel.createInitialResume(
                student.id(),
                student.name(),
                new ResumeElementDto.Writer.SchoolInfo(
                        classInfo.grade(),
                        classInfo.classNumber(),
                        classInfo.number(),
                        classInfo.schoolNumber(),
                        Year.now().getValue() - 2013 - classInfo.grade()
                ),
                new ResumeElementDto.Writer.Major(
                        major.id(),
                        major.name()
                )
        );
    }
}
