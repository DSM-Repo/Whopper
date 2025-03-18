package com.dsm.repo.internal.core.domain.event.teacher.impl;

import com.dsm.repo.external.exception.domain.teacher.TeacherNotFoundException;
import com.dsm.repo.internal.core.domain.event.teacher.CurrentTeacher;
import com.dsm.repo.internal.core.domain.model.teacher.TeacherModel;
import com.dsm.repo.internal.data.repository.teacher.TeacherRepository;
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
