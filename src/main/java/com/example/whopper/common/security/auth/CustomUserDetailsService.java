package com.example.whopper.common.security.auth;

import com.example.whopper.common.exception.student.StudentNotFoundException;
import com.example.whopper.domain.student.StudentRepository;
import com.example.whopper.domain.teacher.TeacherMongoRepository;
import com.example.whopper.common.exception.teacher.TeacherNotFoundException;
import com.example.whopper.common.security.jwt.JwtProperties;
import com.example.whopper.interfaces.auth.dto.AuthElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;
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

        return new CustomUserDetails(userId, type);
    }

    private String handleTeacher(String id) {
        if (!teacherMongoRepository.existsById(id)) {
            throw TeacherNotFoundException.EXCEPTION;
        }

        return AuthElementDto.UserRole.TEACHER.name();
    }

    private String handleStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw StudentNotFoundException.EXCEPTION;
        }

        return AuthElementDto.UserRole.STUDENT.name();
    }
}
