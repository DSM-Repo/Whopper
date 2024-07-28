package com.example.whopper.domain.student.application.impl;

import com.example.whopper.domain.major.dao.MajorRepository;
import com.example.whopper.domain.student.application.usecase.GetCurrentUserInfoUseCase;
import com.example.whopper.domain.student.dto.GetCurrentUserInfoResponse;
import com.example.whopper.global.utils.current.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCurrentUserInfoService implements GetCurrentUserInfoUseCase {
    private final MajorRepository majorRepository;
    private final CurrentUser currentUser;

    @Override
    public GetCurrentUserInfoResponse get() {
        var currentStudent = currentUser.getUser();
        return GetCurrentUserInfoResponse.fromStudentEntity(
                currentStudent,
                majorRepository.getById(currentStudent.getMajorId())
        );
    }
}
