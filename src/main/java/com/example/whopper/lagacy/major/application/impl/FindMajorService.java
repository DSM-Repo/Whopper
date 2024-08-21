package com.example.whopper.lagacy.major.application.impl;

import com.example.whopper.lagacy.major.application.usecase.FindMajorUseCase;
import com.example.whopper.lagacy.major.dao.MajorRepository;
import com.example.whopper.lagacy.major.domain.MajorEntity;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindMajorService implements FindMajorUseCase {
    private final MajorRepository majorRepository;

    @Override
    public DataResponseInfo<MajorEntity> findAll() {
        return DataResponseInfo.of(majorRepository.findAll());
    }
}
