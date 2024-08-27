package com.example.whopper.application.teacher.component;

import com.example.whopper.common.exception.auth.NotAuthenticatedException;
import com.example.whopper.domain.teacher.TeacherModel;
import com.example.whopper.common.exception.teacher.TeacherNotFoundException;
import com.example.whopper.domain.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherComponent {

    private final TeacherRepository teacherRepository;

    public TeacherModel currentTeacher() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new NotAuthenticatedException("인증되지 않는 유저입니다.");
        }

        String id = authentication.getName();

        return teacherRepository.findById(id)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }
}