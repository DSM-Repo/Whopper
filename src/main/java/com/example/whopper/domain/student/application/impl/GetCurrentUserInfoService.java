package com.example.whopper.domain.student.application.impl;

import com.example.whopper.domain.major.dao.MajorRepository;
import com.example.whopper.domain.student.application.usecase.GetCurrentUserInfoUseCase;
import com.example.whopper.domain.student.dto.GetCurrentUserInfoResponse;
import com.example.whopper.global.utils.current.CurrentStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCurrentUserInfoService implements GetCurrentUserInfoUseCase {
    private final MajorRepository majorRepository;
    private final CurrentStudent currentStudent;

    @Override
    public GetCurrentUserInfoResponse get() {
        var currentStudent = this.currentStudent.getStudent();
        return GetCurrentUserInfoResponse.fromStudentEntity(
                currentStudent,
                majorRepository.getById(currentStudent.getMajorId())
        );
    }
}
