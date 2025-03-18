package com.dsm.repo.internal.core.domain.event.student;

public record StudentMajorUpdateEvent(
        String majorName,
        String studentId
) {
}
