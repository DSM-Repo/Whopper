package com.example.whopper.domain.teacher;

import com.example.whopper.infrastructure.xquare.dto.XquareUserResponse;
import lombok.Builder;

@Builder
public record TeacherModel(
        String id,
        String name,
        String accountId,
        String password
) {
    public TeacherModel(XquareUserResponse xquareUserResponse) {
        this(null, xquareUserResponse.getName(), xquareUserResponse.getAccountId(), xquareUserResponse.getPassword());
    }
}
