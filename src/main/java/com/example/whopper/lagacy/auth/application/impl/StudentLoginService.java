package com.example.whopper.lagacy.auth.application.impl;

import com.example.whopper.lagacy.auth.application.usecase.StudentLoginUseCase;
import com.example.whopper.lagacy.auth.domain.type.UserRole;
import com.example.whopper.lagacy.auth.dto.request.LoginRequest;
import com.example.whopper.lagacy.auth.dto.response.TokenResponse;
import com.example.whopper.lagacy.auth.exception.InvalidUserException;
import com.example.whopper.lagacy.auth.exception.PasswordMismatchException;
import com.example.whopper.lagacy.document.application.component.CreateDocumentComponent;
import com.example.whopper.lagacy.file.domain.DefaultProfileImageProperties;
import com.example.whopper.lagacy.major.domain.DefaultMajorFacade;
import com.example.whopper.lagacy.student.dao.StudentMongoRepository;
import com.example.whopper.lagacy.student.domain.StudentEntity;
import com.example.whopper.lagacy.student.exception.StudentNotFoundException;
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
    private final CreateDocumentComponent createDocumentComponent;
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

        createDocumentComponent.create(newStudent);
        return getTokenResponse(newStudent.getId());
    }

    private TokenResponse getTokenResponse(String id) {
        return jwtTokenProvider.receiveToken(id, UserRole.STUDENT);
    }

    private StudentEntity createAndSaveNewStudent(XquareUserResponse xquareUserResponse) {
        return studentMongoRepository.save(
                StudentEntity.builder()
                        .accountId(xquareUserResponse.getAccountId())
                        .password(xquareUserResponse.getPassword())
                        .name(xquareUserResponse.getName())
                        .classInfo(xquareUserResponse.toClassInfo())
                        .profileImagePath(defaultProfileImageProperties.imageUrl())
                        .major(defaultMajorFacade.getDefaultMajor())
                        .build());
    }
}