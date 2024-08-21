package com.example.whopper.lagacy.major.application.usecase;

import com.example.whopper.lagacy.major.domain.MajorEntity;
import com.example.whopper.global.utils.DataResponseInfo;

public interface FindMajorUseCase {
    DataResponseInfo<MajorEntity> findAll();
}
