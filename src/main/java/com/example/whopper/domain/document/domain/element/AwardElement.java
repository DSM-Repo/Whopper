package com.example.whopper.domain.document.domain.element;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record AwardElement(
        String name,
        String awardingInstitution,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
        Date awardsDate,
        String description
) {
}
