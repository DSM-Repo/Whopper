package com.example.whopper.domain.feedback.domain;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.feedback.domain.element.FeedbackType;
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
    private FeedbackType type;
    private String content;
    private String writerName;

    @DBRef(lazy = true)
    private DocumentEntity document;

    @Builder
    public FeedbackEntity(FeedbackType type, String content, String writerName, DocumentEntity document) {
        this.type = type;
        this.content = content;
        this.writerName = writerName;
        this.document = document;
    }
}
