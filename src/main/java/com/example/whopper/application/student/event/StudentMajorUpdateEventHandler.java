package com.example.whopper.application.student.event;

import com.example.whopper.application.student.component.CurrentStudent;
import com.example.whopper.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class StudentMajorUpdateEventHandler {
    private final StudentRepository studentRepository;
    private final CurrentStudent currentStudent;

    @Async
    @EventListener(StudentMajorUpdateEvent.class)
    public void handle(StudentMajorUpdateEvent event) {
        var student = currentStudent.getStudent();
        var newStudent = student.updateMajor(event.majorId(), event.majorName());

        studentRepository.save(newStudent);
    }
}
