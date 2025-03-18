package com.dsm.repo.internal.core.domain.event.student;

import com.dsm.repo.external.exception.domain.student.StudentNotFoundException;
import com.dsm.repo.internal.data.repository.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class StudentMajorUpdateEventHandler {
    private final StudentRepository studentRepository;

    @Async
    @Transactional
    @TransactionalEventListener(StudentMajorUpdateEvent.class)
    public void handle(StudentMajorUpdateEvent event) {
        final var student = studentRepository.findById(event.studentId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
        final var newStudent = student.updateMajor(event.majorName());

        studentRepository.save(newStudent);
    }
}
