package com.example.whopper.domain.document.domain;

import com.example.whopper.domain.document.domain.element.*;
import com.example.whopper.domain.student.domain.StudentEntity;
import lombok.Builder;
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

    @Builder
    public DocumentEntity(DocumentStatus status, WriterInfoElement writer, StudentEntity student) {
        this.year = Year.now().getValue();
        this.status = status;
        this.writer = writer;
        this.introduce = IntroduceElement.createEmptyElement();
        this.projectList = Collections.emptyList();
        this.awardList = Collections.emptyList();
        this.certificateList = Collections.emptyList();
        this.activityList = Collections.emptyList();
        this.student = student;
    }

    public static DocumentEntity createForNewStudent(StudentEntity student) {
        return DocumentEntity.builder()
                .status(DocumentStatus.CREATED)
                .writer(WriterInfoElement.createEmptyElement(student))
                .student(student)
                .build();
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
}