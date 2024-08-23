package com.example.whopper.application.major.usecase;

import com.example.whopper.domain.major.MajorModel;
import com.example.whopper.global.utils.DataResponseInfo;

public interface FindMajorUseCase {
    DataResponseInfo<MajorModel> findAll();
}
