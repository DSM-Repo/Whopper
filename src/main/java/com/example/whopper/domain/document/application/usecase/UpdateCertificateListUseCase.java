package com.example.whopper.domain.document.application.usecase;

import com.example.whopper.domain.document.domain.element.CertificateElement;

import java.util.List;

public interface UpdateCertificateListUseCase {
    void update(List<CertificateElement> request);
}
