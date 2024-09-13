package com.repo.whopper.application.auth.service;

import com.repo.whopper.application.auth.usecase.TeacherLoginUseCase;
import com.repo.whopper.common.error.exception.ErrorCode;
import com.repo.whopper.common.exception.external.ExternalException;
import com.repo.whopper.domain.teacher.TeacherModel;
import com.repo.whopper.domain.teacher.TeacherRepository;
import com.repo.whopper.interfaces.auth.dto.AuthElementDto;
import com.repo.whopper.interfaces.auth.dto.request.LoginRequest;
import com.repo.whopper.interfaces.auth.dto.response.TokenResponse;
import com.repo.whopper.common.exception.auth.InvalidUserException;
import com.repo.whopper.common.exception.auth.PasswordMismatchException;
import com.repo.whopper.common.exception.teacher.TeacherNotFoundException;
import com.repo.whopper.common.security.jwt.JwtTokenProvider;
import com.repo.whopper.infrastructure.xquare.XquareClient;
import com.repo.whopper.infrastructure.xquare.dto.XquareUserResponse;
import com.repo.whopper.infrastructure.xquare.exception.XquareException;
import feign.FeignException;
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
        } catch (FeignException e) {
            final var status = e.status();
            if (status == 401) {
                throw new ExternalException(ErrorCode.LOGIN_FAILED);
            }
            else {
                throw new ExternalException(ErrorCode.XQUARE);
            }
        }

        if(!xquareResponse.getUserRole().equals("SCH")) throw InvalidUserException.EXCEPTION;
        final var newTeacher = createAndSaveNewTeacher(xquareResponse);

        return jwtTokenProvider.receiveToken(newTeacher.id(), AuthElementDto.UserRole.TEACHER);
    }

    private TeacherModel createAndSaveNewTeacher(XquareUserResponse xquareResponse) {
        return teacherRepository.save(new TeacherModel(xquareResponse));
    }
}
