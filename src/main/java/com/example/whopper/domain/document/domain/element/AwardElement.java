package com.example.whopper.domain.document.domain.element;

import java.util.Date;

public record AwardElement(
        String name,
        String awardingInstitution,
        Date awardsDate,
        String description
) {
}
