package com.example.whopper.domain.document.domain;

import com.example.whopper.domain.document.domain.element.*;
import com.example.whopper.domain.student.domain.StudentEntity;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Year;
import java.util.Collections;
import java.util.List;

@Getter
@Document(collection = "document_repo")
public class DocumentEntity {
    @Id
    private String id;
    private Integer year;
    private DocumentStatus status;
    private WriterInfoElement writer;
    private IntroduceElement introduce;
    private List<ProjectElement> projectList;
    private List<AwardElement> awardList;
    private List<CertificateElement> certificateList;
    private List<ActivityElement> activityList;

    @DBRef(lazy = true)
    private StudentEntity student;

    private DocumentEntity(Builder builder) {
        this.year = Year.now().getValue();
        this.status = builder.status;
        this.writer = builder.writer;
        this.introduce = builder.introduce;
        this.projectList = builder.projectList;
        this.awardList = builder.awardList;
        this.certificateList = builder.certificateList;
        this.activityList = builder.activityList;
        this.student = builder.student;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void updateDocumentStatus(DocumentStatus status) {
        this.status = status;
    }

    public void updateWriterInfo(WriterInfoElement writer) {
        this.writer = writer;
    }

    public void updateProjectList(List<ProjectElement> projectList) {
        this.projectList = projectList;
    }

    public void updateIntroduceElement(IntroduceElement introduce) {
        this.introduce = introduce;
    }

    public void updateAwardList(List<AwardElement> awardList) {
        this.awardList = awardList;
    }

    public void updateCertificateElement(List<CertificateElement> certificateList) {
        this.certificateList = certificateList;
    }

    public void updateActivityElement(List<ActivityElement> activityList) {
        this.activityList = activityList;
    }

    public static class Builder {
        private DocumentStatus status;
        private WriterInfoElement writer;
        private IntroduceElement introduce;
        private List<ProjectElement> projectList = Collections.emptyList();
        private List<AwardElement> awardList = Collections.emptyList();
        private List<CertificateElement> certificateList = Collections.emptyList();
        private List<ActivityElement> activityList = Collections.emptyList();
        private StudentEntity student;

        public Builder status(DocumentStatus status) {
            this.status = status;
            return this;
        }

        public Builder writer(WriterInfoElement writer) {
            this.writer = writer;
            return this;
        }

        public Builder introduce(IntroduceElement introduce) {
            this.introduce = introduce;
            return this;
        }

        public Builder projectList(List<ProjectElement> projectList) {
            this.projectList = projectList;
            return this;
        }

        public Builder awardList(List<AwardElement> awardList) {
            this.awardList = awardList;
            return this;
        }

        public Builder certificateList(List<CertificateElement> certificateList) {
            this.certificateList = certificateList;
            return this;
        }

        public Builder activityList(List<ActivityElement> activityList) {
            this.activityList = activityList;
            return this;
        }

        public Builder student(StudentEntity student) {
            this.student = student;
            return this;
        }

        public DocumentEntity build() {
            return new DocumentEntity(this);
        }
    }
}