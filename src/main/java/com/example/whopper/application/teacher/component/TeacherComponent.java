package com.example.whopper.application.teacher.component;

import com.example.whopper.common.exception.auth.NotAuthenticatedException;
import com.example.whopper.domain.teacher.TeacherMongoRepository;
import com.example.whopper.domain.teacher.TeacherEntity;
import com.example.whopper.common.exception.teacher.TeacherNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherComponent {

    private final TeacherMongoRepository teacherMongoRepository;

    public TeacherEntity currentTeacher() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new NotAuthenticatedException("인증되지 않는 유저입니다.");
        }

        String id = authentication.getName();

        return teacherMongoRepository.findById(id)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }
}