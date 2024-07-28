package com.example.whopper.domain.document.domain.element;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record ActivityElement(
        String name,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
        Date date,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
        Date endDate,
        boolean isPeriod,
        String description
) {
}
