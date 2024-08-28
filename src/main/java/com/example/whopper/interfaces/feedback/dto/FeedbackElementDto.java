package com.example.whopper.interfaces.feedback.dto;

public class FeedbackElementDto {
    public record Writer(
            String id,
            String name
    ) {}

    public enum Status {
        CONFIRMED,
        PENDING
    }

    public enum Type {
        PROJECT, ACTIVITY, WRITER_INFO, INTRODUCE, ACHIEVEMENT
    }
}
