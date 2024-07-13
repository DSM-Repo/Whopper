package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.application.base.UpdateElementUseCaseBase;
import com.example.whopper.domain.document.domain.element.CertificateElement;

import java.util.List;

public interface UpdateCertificateListUseCase extends UpdateElementUseCaseBase<List<CertificateElement>> {
    void update(List<CertificateElement> request);
}
