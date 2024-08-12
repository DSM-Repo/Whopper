package com.example.whopper.domain.feedback.domain;

import com.example.whopper.domain.document.domain.DocumentEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "feedback_repo")
public class FeedbackEntity {
    @Id
    private String id;
    private String comment;
    private final String writerName;
    private final String elementId;

    private final String documentId;

    @Builder
    public FeedbackEntity(String comment, String writerName, String elementId, String documentId) {
        this.comment = comment;
        this.writerName = writerName;
        this.elementId = elementId;
        this.documentId = documentId;
    }

    public void updateFeedback(String comment) {
        this.comment = comment;
    }
}
