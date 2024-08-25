package com.example.whopper.domain.feedback;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "feedback_repo")
class FeedbackEntity {
    @Id
    private String id;
    private String comment;
    private Type type;
    private String resumeId;
    private Status status;
    private Boolean rejected;
    private Writer writer;

    record Writer(
            String id,
            String name
    ) {}

    enum Status {
        CONFIRMED,
        PENDING
    }

    enum Type {
        PROJECT, ACTIVITY, WRITER_INFO, INTRODUCE, ACHIEVEMENT
    }

    protected FeedbackEntity() {}
}
