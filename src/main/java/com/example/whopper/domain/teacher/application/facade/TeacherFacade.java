package com.example.whopper.domain.teacher.application.facade;

import com.example.whopper.domain.auth.exception.NotAuthenticatedException;
import com.example.whopper.domain.teacher.dao.TeacherMongoRepository;
import com.example.whopper.domain.teacher.domain.TeacherEntity;
import com.example.whopper.domain.teacher.exception.TeacherNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherFacade {

    private final TeacherMongoRepository teacherMongoRepository;

    public TeacherEntity currentTeacher() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new NotAuthenticatedException("인증되지 않는 유저입니다.");
        }

        String accountId = authentication.getName();

        return teacherMongoRepository.findFirstByAccountId(accountId)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }
}