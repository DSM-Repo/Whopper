package com.example.whopper.domain.document.domain.element;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record CertificateElement(
        String name,
        String issuingInstitution,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
        Date issueDate
) {
}
