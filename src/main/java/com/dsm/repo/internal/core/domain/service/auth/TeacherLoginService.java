package com.dsm.repo.internal.core.domain.service.auth;

import com.dsm.repo.internal.core.usecase.auth.TeacherLoginUseCase;
import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.ExternalException;
import com.dsm.repo.internal.core.domain.model.teacher.TeacherModel;
import com.dsm.repo.internal.data.repository.teacher.TeacherRepository;
import com.dsm.repo.external.web.rest.auth.dto.AuthElementDto;
import com.dsm.repo.external.web.rest.auth.dto.request.LoginRequest;
import com.dsm.repo.external.web.rest.auth.dto.response.TokenResponse;
import com.dsm.repo.external.exception.domain.auth.InvalidUserException;
import com.dsm.repo.external.exception.domain.auth.PasswordMismatchException;
import com.dsm.repo.external.exception.domain.teacher.TeacherNotFoundException;
import com.dsm.repo.external.security.jwt.JwtTokenProvider;
import com.dsm.repo.external.xquare.XquareClient;
import com.dsm.repo.external.xquare.dto.XquareUserResponse;
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
