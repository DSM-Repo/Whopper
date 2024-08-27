package com.example.whopper.common.security.auth;

import com.example.whopper.domain.refreshtoken.type.UserRole;
import com.example.whopper.common.exception.student.StudentNotFoundException;
import com.example.whopper.domain.student.StudentRepository;
import com.example.whopper.common.exception.teacher.TeacherNotFoundException;
import com.example.whopper.common.security.jwt.JwtProperties;
import com.example.whopper.domain.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final JwtProperties jwtProperties;

    @Override
    @Transactional(readOnly = true)
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

    private String handleTeacher(String teacherId) {
        if (!teacherRepository.existsById(teacherId)) {
            throw TeacherNotFoundException.EXCEPTION;
        }

        return UserRole.TEACHER.name();
    }

    private String handleStudent(String studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw StudentNotFoundException.EXCEPTION;
        }

        return UserRole.STUDENT.name();
    }
}
