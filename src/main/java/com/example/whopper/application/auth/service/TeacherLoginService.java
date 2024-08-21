package com.example.whopper.application.auth.service;

import com.example.whopper.application.auth.usecase.TeacherLoginUseCase;
import com.example.whopper.domain.refreshtoken.type.UserRole;
import com.example.whopper.interfaces.auth.dto.request.LoginRequest;
import com.example.whopper.interfaces.auth.dto.response.TokenResponse;
import com.example.whopper.common.exception.auth.InvalidUserException;
import com.example.whopper.common.exception.auth.PasswordMismatchException;
import com.example.whopper.lagacy.teacher.dao.TeacherMongoRepository;
import com.example.whopper.lagacy.teacher.domain.TeacherEntity;
import com.example.whopper.lagacy.teacher.exception.TeacherNotFoundException;
import com.example.whopper.common.security.jwt.JwtTokenProvider;
import com.example.whopper.infrastructure.xquare.XquareClient;
import com.example.whopper.infrastructure.xquare.dto.response.XquareUserResponse;
import com.example.whopper.infrastructure.xquare.exception.XquareException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TeacherLoginService implements TeacherLoginUseCase {

    private final TeacherMongoRepository teacherMongoRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final XquareClient xquareClient;
    private final PasswordEncoder passwordEncoder;

    @Override
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
        XquareUserResponse xquareUserResponse;

        try {
            xquareUserResponse = xquareClient.xquareUser(request);
        } catch (Exception e) {
            throw XquareException.EXCEPTION;
        }

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
