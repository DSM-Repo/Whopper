package com.repo.whopper.application.teacher.component;

import com.repo.whopper.common.exception.teacher.TeacherNotFoundException;
import com.repo.whopper.domain.teacher.TeacherModel;
import com.repo.whopper.domain.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CurrentTeacherImpl implements CurrentTeacher {
    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public TeacherModel getTeacher() {
        final var id = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return teacherRepository.findById(id)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }
}
