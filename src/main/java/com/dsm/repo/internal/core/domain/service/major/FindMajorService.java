package com.dsm.repo.internal.core.domain.service.major;

import com.dsm.repo.internal.core.usecase.major.FindMajorUseCase;
import com.dsm.repo.internal.core.domain.model.major.MajorModel;
import com.dsm.repo.internal.data.repository.major.MajorRepository;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;
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
