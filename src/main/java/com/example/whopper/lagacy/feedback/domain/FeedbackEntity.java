package com.example.whopper.lagacy.feedback.domain;

import com.example.whopper.lagacy.document.domain.element.type.DocumentElementType;
import com.example.whopper.lagacy.teacher.domain.TeacherEntity;
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
    private DocumentElementType type;

    private String documentId;

    private Status status;

    @DBRef(lazy = true)
    private TeacherEntity teacher;

    @Builder
    public FeedbackEntity(String comment, DocumentElementType type, String documentId, TeacherEntity teacher) {
        this.comment = comment;
        this.type = type;
        this.documentId = documentId;
        status = Status.PENDING;
        this.teacher = teacher;
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
