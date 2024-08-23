package com.example.whopper.application.major.impl;

import com.example.whopper.application.major.usecase.FindMajorUseCase;
import com.example.whopper.domain.major.MajorModel;
import com.example.whopper.domain.major.MajorRepository;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindMajorService implements FindMajorUseCase {
    private final MajorRepository majorRepository;

    @Override
    public DataResponseInfo<MajorModel> findAll() {
        return DataResponseInfo.of(majorRepository.findAll());
    }
}
