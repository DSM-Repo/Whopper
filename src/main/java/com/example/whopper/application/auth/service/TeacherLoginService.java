package com.example.whopper.application.auth.service;

import com.example.whopper.application.auth.usecase.TeacherLoginUseCase;
import com.example.whopper.domain.teacher.TeacherModel;
import com.example.whopper.domain.teacher.TeacherRepository;
import com.example.whopper.interfaces.auth.dto.AuthElementDto;
import com.example.whopper.interfaces.auth.dto.request.LoginRequest;
import com.example.whopper.interfaces.auth.dto.response.TokenResponse;
import com.example.whopper.common.exception.auth.InvalidUserException;
import com.example.whopper.common.exception.auth.PasswordMismatchException;
import com.example.whopper.common.exception.teacher.TeacherNotFoundException;
import com.example.whopper.common.security.jwt.JwtTokenProvider;
import com.example.whopper.infrastructure.xquare.XquareClient;
import com.example.whopper.infrastructure.xquare.dto.XquareUserResponse;
import com.example.whopper.infrastructure.xquare.exception.XquareException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class TeacherLoginService implements TeacherLoginUseCase {
    private final TeacherRepository teacherRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final XquareClient xquareClient;

    @Override
    @Transactional
    public TokenResponse teacherLogin(LoginRequest request) {
        return teacherRepository.existsByAccountId(request.accountId())
                ? loginExistingTeacher(request)
                : registerAndLoginNewTeacher(request);
    }

    private TokenResponse loginExistingTeacher(LoginRequest request) {
        final var teacher = teacherRepository.findByAccountId(request.accountId())
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.password(), teacher.password())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return jwtTokenProvider.receiveToken(teacher.id(), AuthElementDto.UserRole.TEACHER);
    }

    private TokenResponse registerAndLoginNewTeacher(LoginRequest request) {
        XquareUserResponse xquareResponse;

        try {
            xquareResponse = xquareClient.xquareUser(request);
        } catch (Exception e) {
            throw XquareException.EXCEPTION;
        }

        if(!xquareResponse.getUserRole().equals("SCH")) throw InvalidUserException.EXCEPTION;
        final var newTeacher = createAndSaveNewTeacher(xquareResponse);

        return jwtTokenProvider.receiveToken(newTeacher.id(), AuthElementDto.UserRole.TEACHER);
    }

    private TeacherModel createAndSaveNewTeacher(XquareUserResponse xquareResponse) {
        return teacherRepository.save(new TeacherModel(xquareResponse));
    }
}
