package com.example.whopper.application.auth.service;

import com.example.whopper.application.auth.usecase.StudentLoginUseCase;
import com.example.whopper.domain.refreshtoken.type.UserRole;
import com.example.whopper.interfaces.auth.dto.request.LoginRequest;
import com.example.whopper.interfaces.auth.dto.response.TokenResponse;
import com.example.whopper.common.exception.auth.InvalidUserException;
import com.example.whopper.common.exception.auth.PasswordMismatchException;
import com.example.whopper.application.resume.component.CreateResumeComponent;
import com.example.whopper.domain.file.DefaultProfileImageProperties;
import com.example.whopper.domain.major.DefaultMajorFacade;
import com.example.whopper.domain.student.StudentMongoRepository;
import com.example.whopper.domain.student.StudentEntity;
import com.example.whopper.common.exception.student.StudentNotFoundException;
import com.example.whopper.common.security.jwt.JwtTokenProvider;
import com.example.whopper.infrastructure.xquare.XquareClient;
import com.example.whopper.infrastructure.xquare.dto.response.XquareUserResponse;
import com.example.whopper.infrastructure.xquare.exception.XquareException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class StudentLoginService implements StudentLoginUseCase {

    private final StudentMongoRepository studentMongoRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final XquareClient xquareClient;
    private final PasswordEncoder passwordEncoder;
    private final DefaultMajorFacade defaultMajorFacade;
    private final CreateResumeComponent createResumeComponent;
    private final DefaultProfileImageProperties defaultProfileImageProperties;

    @Override
    @Transactional
    public TokenResponse studentLogin(LoginRequest request) {
        return studentMongoRepository.existsByAccountId(request.accountId())
                ? loginExistingStudent(request)
                : registerAndLoginNewStudent(request);
    }

    private TokenResponse loginExistingStudent(LoginRequest request) {
        StudentEntity student = studentMongoRepository.findFirstByAccountId(request.accountId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.password(), student.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return getTokenResponse(student.getId());
    }

    private TokenResponse registerAndLoginNewStudent(LoginRequest request) {
        XquareUserResponse xquareUserResponse;

        try {
            xquareUserResponse = xquareClient.xquareUser(request);
        } catch (Exception e) {
            throw XquareException.EXCEPTION;
        }

        if(!xquareUserResponse.getUserRole().equals("STU")) throw InvalidUserException.EXCEPTION;
        StudentEntity newStudent = createAndSaveNewStudent(xquareUserResponse);

        createResumeComponent.create(newStudent);
        return getTokenResponse(newStudent.getId());
    }

    private TokenResponse getTokenResponse(String id) {
        return jwtTokenProvider.receiveToken(id, UserRole.STUDENT);
    }

    private StudentEntity createAndSaveNewStudent(XquareUserResponse xquareUserResponse) {
        var defaultMajor = defaultMajorFacade.getDefaultMajor();

        return studentMongoRepository.save(
                StudentEntity.builder()
                        .accountId(xquareUserResponse.getAccountId())
                        .password(xquareUserResponse.getPassword())
                        .name(xquareUserResponse.getName())
                        .classInfo(xquareUserResponse.toClassInfo())
                        .profileImagePath(defaultProfileImageProperties.imageUrl())
                        .major(new StudentEntity.Major(defaultMajor.id(), defaultMajor.name()))
                        .build());
    }
}