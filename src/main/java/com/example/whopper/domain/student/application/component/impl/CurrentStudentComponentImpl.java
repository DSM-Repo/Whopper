package com.example.whopper.domain.student.application.component.impl;

import com.example.whopper.domain.auth.exception.NotAuthenticatedException;
import com.example.whopper.domain.student.application.component.usecase.CurrentStudentComponent;
import com.example.whopper.domain.student.dao.StudentMongoRepository;
import com.example.whopper.domain.student.domain.StudentEntity;
import com.example.whopper.domain.student.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentStudentComponentImpl implements CurrentStudentComponent {

    private final StudentMongoRepository studentMongoRepository;

    public StudentEntity currentStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new NotAuthenticatedException("인증되지 않는 유저입니다.");
        }

        String accountId = authentication.getName();

        return studentMongoRepository.findFirstByAccountId(accountId)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
    }
}
