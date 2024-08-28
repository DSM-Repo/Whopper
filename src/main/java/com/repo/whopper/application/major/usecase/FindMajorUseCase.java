package com.repo.whopper.application.major.usecase;

import com.repo.whopper.domain.major.MajorModel;
import com.repo.whopper.common.http.dto.DataResponseInfo;

public interface FindMajorUseCase {
    DataResponseInfo<MajorModel> findAll();
}
