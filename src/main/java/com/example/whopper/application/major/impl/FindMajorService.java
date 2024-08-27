package com.example.whopper.application.major.impl;

import com.example.whopper.application.major.usecase.FindMajorUseCase;
import com.example.whopper.domain.major.MajorModel;
import com.example.whopper.domain.major.MajorRepository;
import com.example.whopper.common.http.response.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindMajorService implements FindMajorUseCase {
    private final MajorRepository majorRepository;

    @Override
    @Transactional(readOnly = true)
    public DataResponseInfo<MajorModel> findAll() {
        return DataResponseInfo.of(majorRepository.findAll());
    }
}
