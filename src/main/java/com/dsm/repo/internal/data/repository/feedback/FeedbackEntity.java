package com.dsm.repo.internal.data.repository.feedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@AllArgsConstructor
@Document(collection = "feedback_repo")
public class FeedbackEntity {
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
