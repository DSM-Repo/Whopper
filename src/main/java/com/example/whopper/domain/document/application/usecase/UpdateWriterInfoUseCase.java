package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.dto.request.UpdateWriterInfoRequest;

public interface UpdateWriterInfoUseCase {
    void update(UpdateWriterInfoRequest request);
}
