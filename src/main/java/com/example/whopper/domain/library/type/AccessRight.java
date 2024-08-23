package com.example.whopper.domain.library.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AccessRight {
    PUBLIC("전체 조회 가능"),
    PRIVATE("학생, 선생님만 조회 가능");

    private final String description;
}
