package com.example.whopper.global.security.auth;

import com.example.whopper.domain.auth.domain.type.LoginType;
import com.example.whopper.domain.student.dao.StudentMongoRepository;
import com.example.whopper.domain.student.domain.StudentEntity;
import com.example.whopper.domain.student.exception.StudentNotFoundException;
import com.example.whopper.domain.teacher.dao.TeacherMongoRepository;
import com.example.whopper.domain.teacher.domain.TeacherEntity;
import com.example.whopper.domain.teacher.exception.TeacherNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final StudentMongoRepository studentMongoRepository;
    private final TeacherMongoRepository teacherMongoRepository;

    public UserDetails loadUserByUsername(String id, String loginType) {
        var type = LoginType.valueOf(loginType);

        final var user = type.equals(LoginType.STUDENT)
                ? handleStudent(id)
                : handleTeacher(id);

        return new CustomUserDetails(user);
    }

    private String handleTeacher(String id) {
        TeacherEntity teacher = teacherMongoRepository.findById(id)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        return teacher.getId();
    }

    private String handleStudent(String id) {
        StudentEntity student = studentMongoRepository.findById(id)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
        return student.getId();
    }
}
