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
    private String content;
    private String writerName;
    private String elementId;

    @DBRef(lazy = true)
    private DocumentEntity document;

    @Builder
    public FeedbackEntity(String content, String writerName, String elementId, DocumentEntity document) {
        this.content = content;
        this.writerName = writerName;
        this.elementId = elementId;
        this.document = document;
    }
}
