package com.example.whopper.domain.auth.application.impl;

import com.example.whopper.domain.auth.application.usecase.TeacherLoginUseCase;
import com.example.whopper.domain.auth.dto.request.LoginRequest;
import com.example.whopper.domain.auth.dto.response.TokenResponse;
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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeacherLoginService implements TeacherLoginUseCase {

    private final TeacherMongoRepository teacherMongoRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final XquareClient xquareClient;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponse teacherLogin(LoginRequest request) {
        if(teacherMongoRepository.existsByAccountId(request.account_id())) {
            TeacherEntity teacher = teacherMongoRepository.findFirstByAccountId(request.account_id())
                    .orElseThrow(()-> TeacherNotFoundException.EXCEPTION);

            if(!passwordEncoder.matches(request.password(), teacher.getPassword())) throw PasswordMismatchException.EXCEPTION;

            return jwtTokenProvider.receiveToken(request.account_id());
        }

        return teacherMongoRepository.existsByAccountId(request.account_id()) ?
                loginExistingTeacher(request) :
                registerAndLoginNewTeacher(request);
    }

    private TokenResponse loginExistingTeacher(LoginRequest request) {
        TeacherEntity teacher = teacherMongoRepository.findFirstByAccountId(request.account_id())
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.password(), teacher.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return jwtTokenProvider.receiveToken(request.account_id());
    }

    private TokenResponse registerAndLoginNewTeacher(LoginRequest request) {
        XquareUserResponse xquareUserResponse = xquareClient.xquareUser(request);
        TeacherEntity newTeacher = createAndSaveNewTeacher(xquareUserResponse);
        return jwtTokenProvider.receiveToken(newTeacher.getId());
    }

    private TeacherEntity createAndSaveNewTeacher(XquareUserResponse xquareUserResponse) {
        return teacherMongoRepository.save(
                TeacherEntity.builder()
                        .account_id(xquareUserResponse.getAccount_id())
                        .password(xquareUserResponse.getPassword())
                        .name(xquareUserResponse.getName())
                        .build());
    }
}
