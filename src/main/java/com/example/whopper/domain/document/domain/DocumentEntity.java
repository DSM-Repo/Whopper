package com.example.whopper.domain.document.domain;

import com.example.whopper.domain.document.domain.element.*;
import com.example.whopper.domain.document.domain.element.base.AbstractElement;
import com.example.whopper.domain.document.domain.element.base.NamedElement;
import com.example.whopper.domain.student.domain.PrivateStudentInfo;
import com.example.whopper.domain.student.domain.StudentEntity;
import com.example.whopper.domain.student.domain.StudentInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private List<AchievementElement> achievementList;
    private List<ActivityElement> activityList;

    private PrivateStudentInfo privateStudentInfo;

    @Field("student")
    @DBRef(lazy = true)
    private StudentEntity student;

    @Builder
    public DocumentEntity(DocumentStatus status, WriterInfoElement writer, StudentEntity student) {
        this.year = Year.now().getValue();
        this.status = status;
        this.writer = writer;
        this.introduce = IntroduceElement.createEmptyElement();
        this.projectList = Collections.emptyList();
        this.achievementList = Collections.emptyList();
        this.activityList = Collections.emptyList();
        this.privateStudentInfo = PrivateStudentInfo.of(
                student.getName(),
                student.getClassInfo().grade(),
                student.getClassInfo().classNumber(),
                student.getMajor().getId(),
                student.getClassInfo().schoolNumber()
        );
        this.student = student;
    }

    protected DocumentEntity() {}

    public void release() {
        status = DocumentStatus.RELEASED;
    }

    public void submit() {
        status = DocumentStatus.SUBMITTED;
    }

    public void onGoing() {
        status = DocumentStatus.ONGOING;
    }

    public void delete() {
        status = DocumentStatus.DELETED;
    }

    public void updateWriterInfo(WriterInfoElement writer) {
        this.writer = writer;
    }

    public void updateProjectList(List<ProjectElement> projectList) {
        this.projectList = projectList;
    }

    private List<? extends AbstractElement> getElementList() {
        return Stream.of(
                        Stream.of(writer, introduce),
                        projectList.stream(),
                        achievementList.stream(),
                        activityList.stream()
                )
                .flatMap(stream -> stream)
                .toList();
    }

    public Map<String, String> getElementNameMap() {
        return getElementList().stream()
                .collect(Collectors.toMap(
                        AbstractElement::getElementId,
                        NamedElement::getName
                ));
    }

    public static DocumentEntity createForNewStudent(StudentEntity student) {
        return DocumentEntity.builder()
                .status(DocumentStatus.ONGOING)
                .writer(WriterInfoElement.createEmptyElement(student))
                .student(student)
                .build();
    }

    public void updateIntroduceElement(IntroduceElement introduce) {
        this.introduce = introduce;
    }

    public void updateAchievementList(List<AchievementElement> achievementList) {
        this.achievementList = achievementList;
    }


    public void updateActivityElement(List<ActivityElement> activityList) {
        this.activityList = activityList;
    }
}

