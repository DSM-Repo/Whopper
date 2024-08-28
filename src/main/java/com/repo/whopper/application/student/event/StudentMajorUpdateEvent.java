package com.repo.whopper.application.student.event;

public record StudentMajorUpdateEvent(
        String majorId,
        String majorName,
        String studentId
) {
}
