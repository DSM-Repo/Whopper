package com.example.whopper.domain.resume;

import com.example.whopper.domain.student.StudentEntity;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;

import java.time.Year;

public class ResumeModelFactory {

    public static ResumeModel createResumeModelFromStudentEntity(StudentEntity student) {
        var classInfo = student.getClassInfo();
        final var major = student.getMajor();

        return ResumeModel.createInitialResume(
                student.getId(),
                student.getName(),
                new ResumeElementDto.Writer.SchoolInfo(
                        classInfo.grade(),
                        classInfo.classNumber(),
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
