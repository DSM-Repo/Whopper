package com.example.whopper.application.student.event;

import com.example.whopper.application.student.component.CurrentStudent;
import com.example.whopper.domain.student.StudentEntity;
import com.example.whopper.domain.student.StudentMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class StudentMajorUpdateEventHandler {
    private final StudentMongoRepository studentMongoRepository;
    private final CurrentStudent currentStudent;

    @Async
    @EventListener(StudentMajorUpdateEvent.class)
    public void handle(StudentMajorUpdateEvent event) {
        var student = currentStudent.getStudent();
        student.updateMajor(new StudentEntity.Major(event.majorId(), event.majorName()));

        studentMongoRepository.save(student);
    }
}
