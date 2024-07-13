package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.application.base.UpdateElementUseCaseBase;
import com.example.whopper.domain.document.dto.request.UpdateWriterInfoRequest;

public interface UpdateWriterInfoUseCase extends UpdateElementUseCaseBase<UpdateWriterInfoRequest> {
    void update(UpdateWriterInfoRequest request);
}
