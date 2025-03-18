package com.dsm.repo.external.security.auth;

import com.dsm.repo.external.exception.domain.auth.InvalidTokenException;
import com.dsm.repo.external.exception.domain.student.StudentNotFoundException;
import com.dsm.repo.internal.data.repository.student.StudentRepository;
import com.dsm.repo.external.exception.domain.teacher.TeacherNotFoundException;
import com.dsm.repo.external.security.jwt.JwtProperties;
import com.dsm.repo.internal.data.repository.teacher.TeacherRepository;
import com.dsm.repo.external.web.rest.auth.dto.AuthElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
class CustomUserDetailsService implements UserDetailsService {

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
            throw InvalidTokenException.EXCEPTION;
        }

        return new CustomUserDetails(userId, type);
    }

    private String handleTeacher(String teacherId) {
        if (!teacherRepository.existsById(teacherId)) {
            throw TeacherNotFoundException.EXCEPTION;
        }

        return AuthElementDto.UserRole.TEACHER.name();
    }

    private String handleStudent(String studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw StudentNotFoundException.EXCEPTION;
        }

        return AuthElementDto.UserRole.STUDENT.name();
    }

}
