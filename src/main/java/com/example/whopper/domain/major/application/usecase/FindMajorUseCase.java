package com.example.whopper.domain.major.application.usecase;

import com.example.whopper.domain.major.domain.MajorEntity;
import com.example.whopper.global.utils.DataResponseInfo;

public interface FindMajorUseCase {
    DataResponseInfo<MajorEntity> findAll();
}
