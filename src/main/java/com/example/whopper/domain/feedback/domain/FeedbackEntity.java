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

    @DBRef(lazy = true)
    private DocumentEntity document;

    @Builder
    public FeedbackEntity(String comment, String writerName, String elementId, DocumentEntity document) {
        this.comment = comment;
        this.writerName = writerName;
        this.elementId = elementId;
        this.document = document;
    }

    public void updateFeedback(String comment) {
        this.comment = comment;
    }
}
