package com.example.whopper.domain.feedback;

import com.example.whopper.domain.resume.element.type.DocumentElementType;
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
    private String documentId;
    private Status status;
    private Boolean rejected;

    record Writer(
            String id,
            String name
    ) {}

    protected FeedbackEntity() {}

    public enum Status {
        CONFIRMED,
        PENDING
    }

    public enum Type {
        PROJECT, ACTIVITY, WRITER_INFO, INTRODUCE, ACHIEVEMENT
    }
}
