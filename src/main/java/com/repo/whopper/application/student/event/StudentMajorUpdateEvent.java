package com.repo.whopper.application.student.event;

public record StudentMajorUpdateEvent(
        String majorName,
        String studentId
) {
}
