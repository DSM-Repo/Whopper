package com.example.whopper.global.security.auth;

import com.example.whopper.domain.auth.domain.type.UserRole;
import com.example.whopper.domain.student.dao.StudentMongoRepository;
import com.example.whopper.domain.student.exception.StudentNotFoundException;
import com.example.whopper.domain.teacher.dao.TeacherMongoRepository;
import com.example.whopper.domain.teacher.exception.TeacherNotFoundException;
import com.example.whopper.global.security.jwt.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentMongoRepository studentMongoRepository;
    private final TeacherMongoRepository teacherMongoRepository;
    private final JwtProperties jwtProperties;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var parts = username.split(":");

        var userId = parts[0];
        var userSecretId = parts[1];
        String type;

        if (userSecretId.equals(jwtProperties.teacherSecret())) {
            type = handleTeacher(userId);
        } else if (userSecretId.equals(jwtProperties.studentSecret())) {
            type = handleStudent(userId);
        } else {
            throw new RuntimeException();
        }

        return new CustomUserDetails(type);
    }

    private String handleTeacher(String id) {
        if (!teacherMongoRepository.existsById(id)) {
            throw TeacherNotFoundException.EXCEPTION;
        }

        return UserRole.TEACHER.name();
    }

    private String handleStudent(String id) {
        if (!studentMongoRepository.existsById(id)) {
            throw StudentNotFoundException.EXCEPTION;
        }

        return UserRole.STUDENT.name();
    }
}
