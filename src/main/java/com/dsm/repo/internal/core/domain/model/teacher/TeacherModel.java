package com.dsm.repo.internal.core.domain.model.teacher;

import com.dsm.repo.external.xquare.dto.XquareUserResponse;
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
