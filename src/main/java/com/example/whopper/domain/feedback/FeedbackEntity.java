package com.example.whopper.domain.feedback;

import com.example.whopper.domain.resume.element.type.DocumentElementType;
import com.example.whopper.domain.teacher.TeacherEntity;
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
    private Boolean rejected;

    @DBRef(lazy = true)
    private TeacherEntity teacher;

    @Builder
    public FeedbackEntity(String comment, DocumentElementType type, String documentId, TeacherEntity teacher) {
        this.comment = comment;
        this.type = type;
        this.documentId = documentId;
        status = Status.PENDING;
        this.teacher = teacher;
        this.rejected = false;
    }

    public void updateFeedback(String comment) {
        this.comment = comment;
    }

    public void confirmed() {
        status = Status.CONFIRMED;
    }

    public void rejected() {
        rejected = true;
    }

    enum Status {
        CONFIRMED,
        PENDING
    }
}
