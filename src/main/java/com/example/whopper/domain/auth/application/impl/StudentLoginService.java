package com.example.whopper.domain.auth.application.impl;

import com.example.whopper.domain.auth.application.usecase.StudentLoginUseCase;
import com.example.whopper.domain.auth.domain.type.UserRole;
import com.example.whopper.domain.auth.dto.request.LoginRequest;
import com.example.whopper.domain.auth.dto.response.TokenResponse;
import com.example.whopper.domain.auth.exception.PasswordMismatchException;
import com.example.whopper.domain.document.application.component.CreateDocumentComponent;
import com.example.whopper.domain.major.domain.DefaultMajorFacade;
import com.example.whopper.domain.student.dao.StudentMongoRepository;
import com.example.whopper.domain.student.domain.StudentEntity;
import com.example.whopper.domain.student.exception.StudentNotFoundException;
import com.example.whopper.global.security.jwt.JwtTokenProvider;
import com.example.whopper.infra.feign.XquareClient;
import com.example.whopper.infra.feign.dto.response.XquareUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentLoginService implements StudentLoginUseCase {

    private final StudentMongoRepository studentMongoRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final XquareClient xquareClient;
    private final PasswordEncoder passwordEncoder;
    private final DefaultMajorFacade defaultMajorFacade;
    private final CreateDocumentComponent createDocumentComponent;

    @Transactional
    public TokenResponse studentLogin(LoginRequest request) {
        return studentMongoRepository.existsByAccountId(request.account_id()) ?
                loginExistingStudent(request) :
                registerAndLoginNewStudent(request);
    }

    private TokenResponse loginExistingStudent(LoginRequest request) {
        StudentEntity student = studentMongoRepository.findFirstByAccountId(request.account_id())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.password(), student.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return getTokenResponse(student.getId());
    }

    private TokenResponse registerAndLoginNewStudent(LoginRequest request) {
        XquareUserResponse xquareUserResponse = xquareClient.xquareUser(request);
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
                        .accountId(xquareUserResponse.getAccount_id())
                        .password(xquareUserResponse.getPassword())
                        .name(xquareUserResponse.getName())
                        .classInfo(xquareUserResponse.toClassInfo())
                        .profileImagePath(xquareUserResponse.getProfileImgUrl())
                        .major(defaultMajorFacade.getDefaultMajor())
                        .build());
    }
}