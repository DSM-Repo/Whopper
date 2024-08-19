package com.example.whopper.domain.auth.application.impl;

import com.example.whopper.domain.auth.application.usecase.TeacherLoginUseCase;
import com.example.whopper.domain.auth.domain.type.UserRole;
import com.example.whopper.domain.auth.dto.request.LoginRequest;
import com.example.whopper.domain.auth.dto.response.TokenResponse;
import com.example.whopper.domain.auth.exception.InvalidUserException;
import com.example.whopper.domain.auth.exception.PasswordMismatchException;
import com.example.whopper.domain.teacher.dao.TeacherMongoRepository;
import com.example.whopper.domain.teacher.domain.TeacherEntity;
import com.example.whopper.domain.teacher.exception.TeacherNotFoundException;
import com.example.whopper.global.security.jwt.JwtTokenProvider;
import com.example.whopper.infra.feign.XquareClient;
import com.example.whopper.infra.feign.dto.response.XquareUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherLoginService implements TeacherLoginUseCase {

    private final TeacherMongoRepository teacherMongoRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final XquareClient xquareClient;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse teacherLogin(LoginRequest request) {
        return teacherMongoRepository.existsByAccountId(request.accountId())
                ? loginExistingTeacher(request)
                : registerAndLoginNewTeacher(request);
    }

    private TokenResponse loginExistingTeacher(LoginRequest request) {
        TeacherEntity teacher = teacherMongoRepository.findByAccountId(request.accountId())
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.password(), teacher.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return jwtTokenProvider.receiveToken(teacher.getId(), UserRole.TEACHER);
    }

    private TokenResponse registerAndLoginNewTeacher(LoginRequest request) {
        XquareUserResponse xquareUserResponse = xquareClient.xquareUser(request);
        if(!xquareUserResponse.getUserRole().equals("SCH")) throw InvalidUserException.EXCEPTION;
        TeacherEntity newTeacher = createAndSaveNewTeacher(xquareUserResponse);

        return jwtTokenProvider.receiveToken(newTeacher.getId(), UserRole.TEACHER);
    }

    private TeacherEntity createAndSaveNewTeacher(XquareUserResponse xquareUserResponse) {
        return teacherMongoRepository.save(
                TeacherEntity.builder()
                        .accountId(xquareUserResponse.getAccountId())
                        .password(xquareUserResponse.getPassword())
                        .name(xquareUserResponse.getName())
                        .build());
    }
}
