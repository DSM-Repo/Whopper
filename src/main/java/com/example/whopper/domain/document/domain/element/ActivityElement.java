package com.example.whopper.domain.document.domain.element;

import java.util.Date;

public record ActivityElement(
        String name,
        Date date,
        Date endDate,
        Boolean isPeriod,
        String description
) {
}
