package com.example.whopper.domain.document.domain.element;

import java.util.Date;

public record CertificateElement(
        String name,
        String issuingInstitution,
        Date issueDate
) {
}
