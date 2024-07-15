package com.example.whopper.domain.auth.application;

import com.example.whopper.domain.auth.dto.request.StudentLoginRequest;
import com.example.whopper.domain.auth.dto.response.TokenResponse;
import com.example.whopper.domain.auth.exception.PasswordMismatchException;
import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
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
public class StudentLoginService {

    private final StudentMongoRepository studentMongoRepository;
    private final DocumentRepository documentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final XquareClient xquareClient;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponse studentLogin(StudentLoginRequest request) {
        return studentMongoRepository.existsByAccountId(request.getAccount_id()) ?
                loginExistingStudent(request) :
                registerAndLoginNewStudent(request);
    }

    private TokenResponse loginExistingStudent(StudentLoginRequest request) {
        StudentEntity student = studentMongoRepository.findFirstByAccountId(request.getAccount_id())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return jwtTokenProvider.receiveToken(request.getAccount_id());
    }

    private TokenResponse registerAndLoginNewStudent(StudentLoginRequest request) {
        XquareUserResponse xquareUserResponse = xquareClient.xquareUser(request);
        StudentEntity newStudent = createAndSaveNewStudent(xquareUserResponse);
        documentRepository.save(DocumentEntity.createForNewStudent(newStudent));
        return jwtTokenProvider.receiveToken(newStudent.getId());
    }

    private StudentEntity createAndSaveNewStudent(XquareUserResponse xquareUserResponse) {
        return studentMongoRepository.save(
                StudentEntity.builder()
                        .accountId(xquareUserResponse.getAccount_id())
                        .password(xquareUserResponse.getPassword())
                        .name(xquareUserResponse.getName())
                        .classInfo(xquareUserResponse.toClassInfo())
                        .profileImagePath(xquareUserResponse.getProfileImgUrl())
                        .build());
    }
}