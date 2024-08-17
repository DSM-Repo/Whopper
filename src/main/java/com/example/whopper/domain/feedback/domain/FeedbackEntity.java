package com.example.whopper.domain.feedback.domain;

import com.example.whopper.domain.document.domain.element.type.DocumentElementType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "feedback_repo")
public class FeedbackEntity {
    @Id
    private String id;
    private String comment;
    private String writerName;
    private String elementId;

    private String documentId;

    private Status status;

    @Builder
    public FeedbackEntity(String comment, String writerName, String elementId, String documentId) {
        this.comment = comment;
        this.writerName = writerName;
        this.elementId = elementId;
        this.documentId = documentId;
        status = Status.PENDING;
    }

    public void updateFeedback(String comment) {
        this.comment = comment;
    }

    public void confirmed() {
        status = Status.CONFIRMED;
    }

    enum Status {
        CONFIRMED,
        PENDING
    }
}
