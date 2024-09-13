package com.repo.whopper.application.auth.service;

import com.repo.whopper.application.auth.usecase.StudentLoginUseCase;
import com.repo.whopper.common.error.exception.ErrorCode;
import com.repo.whopper.common.exception.external.ExternalException;
import com.repo.whopper.domain.student.StudentModel;
import com.repo.whopper.domain.student.StudentRepository;
import com.repo.whopper.interfaces.auth.dto.AuthElementDto;
import com.repo.whopper.interfaces.auth.dto.request.LoginRequest;
import com.repo.whopper.interfaces.auth.dto.response.TokenResponse;
import com.repo.whopper.common.exception.auth.InvalidUserException;
import com.repo.whopper.common.exception.auth.PasswordMismatchException;
import com.repo.whopper.application.resume.component.CreateResumeComponent;
import com.repo.whopper.domain.file.DefaultProfileImageProperties;
import com.repo.whopper.domain.major.DefaultMajorFacade;
import com.repo.whopper.common.exception.student.StudentNotFoundException;
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
class StudentLoginService implements StudentLoginUseCase {
    private final StudentRepository studentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final XquareClient xquareClient;
    private final PasswordEncoder passwordEncoder;
    private final DefaultMajorFacade defaultMajorFacade;
    private final CreateResumeComponent createResumeComponent;
    private final DefaultProfileImageProperties defaultProfileImageProperties;

    @Override
    @Transactional
    public TokenResponse studentLogin(LoginRequest request) {
        return studentRepository.existsByAccountId(request.accountId())
                ? loginExistingStudent(request)
                : registerAndLoginNewStudent(request);
    }

    private TokenResponse loginExistingStudent(LoginRequest request) {
        final var student = studentRepository.findByAccountId(request.accountId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.password(), student.password())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return getTokenResponse(student.id());
    }

    private TokenResponse registerAndLoginNewStudent(LoginRequest request) {
        XquareUserResponse xquareUserResponse;

        try {
            xquareUserResponse = xquareClient.xquareUser(request);
        } catch (FeignException e) {
            final var status = e.status();
            if (status == 401) {
                throw new ExternalException(ErrorCode.LOGIN_FAILED);
            }
            else {
                throw new ExternalException(ErrorCode.XQUARE);
            }
        }

        if(!xquareUserResponse.getUserRole().equals("STU")) throw InvalidUserException.EXCEPTION;
        final var newStudent = createAndSaveNewStudent(xquareUserResponse);

        createResumeComponent.create(newStudent);
        return getTokenResponse(newStudent.id());
    }

    private TokenResponse getTokenResponse(String id) {
        return jwtTokenProvider.receiveToken(id, AuthElementDto.UserRole.STUDENT);
    }

    private StudentModel createAndSaveNewStudent(XquareUserResponse xquareUserResponse) {
        final var defaultMajor = defaultMajorFacade.getDefaultMajor();

        return studentRepository.save(
                new StudentModel(
                        xquareUserResponse.getAccountId(),
                        xquareUserResponse.getPassword(),
                        xquareUserResponse.getName(),
                        xquareUserResponse.getGrade(),
                        xquareUserResponse.getClassNum(),
                        xquareUserResponse.getNum(),
                        defaultProfileImageProperties.imageUrl(),
                        defaultMajor.id()
                )
        );
    }
}