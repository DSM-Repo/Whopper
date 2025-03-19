package com.dsm.repo.internal.core.domain.service.auth;

import com.dsm.repo.external.exception.domain.resume.ResumeNotFoundException;
import com.dsm.repo.external.web.rest.resume.dto.ResumeElementDto;
import com.dsm.repo.external.web.rest.student.dto.StudentElementDto;
import com.dsm.repo.internal.core.domain.model.resume.ResumeModel;
import com.dsm.repo.internal.core.usecase.auth.StudentLoginUseCase;
import com.dsm.repo.external.exception.error.ErrorCode;
import com.dsm.repo.external.exception.ExternalException;
import com.dsm.repo.internal.core.domain.model.student.StudentModel;
import com.dsm.repo.internal.data.repository.resume.ResumeRepository;
import com.dsm.repo.internal.data.repository.student.StudentRepository;
import com.dsm.repo.external.web.rest.auth.dto.AuthElementDto;
import com.dsm.repo.external.web.rest.auth.dto.request.LoginRequest;
import com.dsm.repo.external.web.rest.auth.dto.response.TokenResponse;
import com.dsm.repo.external.exception.domain.auth.InvalidUserException;
import com.dsm.repo.external.exception.domain.auth.PasswordMismatchException;
import com.dsm.repo.internal.core.domain.component.resume.CreateResumeComponent;
import com.dsm.repo.internal.data.property.file.DefaultProfileImageProperties;
import com.dsm.repo.internal.data.repository.major.DefaultMajorFacade;
import com.dsm.repo.external.exception.domain.student.StudentNotFoundException;
import com.dsm.repo.external.security.jwt.JwtTokenProvider;
import com.dsm.repo.external.xquare.XquareClient;
import com.dsm.repo.external.xquare.dto.XquareUserResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;

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

    private final ResumeRepository resumeRepository; // TODO: 3/19/25 안티패턴

    @Override
    @Transactional
    public TokenResponse studentLogin(LoginRequest request) {
        return studentRepository.existsByAccountId(request.accountId())
                ? loginExistingStudent(request)
                : registerAndLoginNewStudent(request);
    }

    private TokenResponse loginExistingStudent(LoginRequest request) {
        final StudentModel student = studentRepository.findByAccountId(request.accountId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.password(), student.password())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        final ResumeModel resume = resumeRepository.findByWriterId(student.id())
                .orElseThrow(() -> ResumeNotFoundException.EXCEPTION);

        if ( // TODO: 3/19/25 안티패턴
            resume.year() != Year.now().getValue() // 현재 년도와 다르면 xquare 요청 후 유저 정보 업데이트
        ) {
            final XquareUserResponse xquareUserResponse = xquareClient.xquareUser(request);

            final StudentElementDto.ClassInfo classInfo = new StudentElementDto.ClassInfo(
                    xquareUserResponse.getGrade(),
                    xquareUserResponse.getClassNum(),
                    xquareUserResponse.getNum()
            );

            final StudentModel updatedStudent = student.updateClassInfo(classInfo);
            studentRepository.save(updatedStudent);

            final ResumeElementDto.Writer.SchoolInfo schoolInfo =  resume.writer().schoolInfo().updateInfo(classInfo);
            final ResumeModel updatedResume = resume.updateYearAndSchoolInfo(schoolInfo);
            resumeRepository.save(updatedResume);
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