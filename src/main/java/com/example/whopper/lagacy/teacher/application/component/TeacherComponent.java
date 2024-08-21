package com.example.whopper.lagacy.teacher.application.component;

import com.example.whopper.lagacy.auth.exception.NotAuthenticatedException;
import com.example.whopper.lagacy.teacher.dao.TeacherMongoRepository;
import com.example.whopper.lagacy.teacher.domain.TeacherEntity;
import com.example.whopper.lagacy.teacher.exception.TeacherNotFoundException;
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