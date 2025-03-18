package com.dsm.repo.internal.core.usecase.major;

import com.dsm.repo.internal.core.domain.model.major.MajorModel;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;

public interface FindMajorUseCase {
    DataResponseInfo<MajorModel> findAll();
}
