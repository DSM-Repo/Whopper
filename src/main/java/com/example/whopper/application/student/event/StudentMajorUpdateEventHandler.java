package com.example.whopper.application.student.event;

import com.example.whopper.common.exception.student.StudentNotFoundException;
import com.example.whopper.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMajorUpdateEventHandler {
    private final StudentRepository studentRepository;

    @Async
    @EventListener(StudentMajorUpdateEvent.class)
    public void handle(StudentMajorUpdateEvent event) {
        var student = studentRepository.findById(event.studentId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
        var newStudent = student.updateMajor(event.majorId(), event.majorName());

        studentRepository.save(newStudent);
    }
}
