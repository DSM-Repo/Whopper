package com.example.whopper.domain.document.domain;

import com.example.whopper.domain.document.domain.element.*;
import com.example.whopper.domain.student.domain.StudentEntity;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Getter
@Document(collection = "document_repo")
public class DocumentEntity {
    @Id
    private String id;

    private Integer year;

    private DocumentStatus status;

    private WriterInfoElement writer;

    private IntroduceElement introduce;

    private Set<String> skillSet;

    private List<ProjectElement> projectList; // 프로젝트 목록

    private List<AwardElement> awardList; // 수상 목록

    private List<CertificateElement> certificateList; // 자격증 목록

    private List<ActivityElement> activityList; // 활동 목록

    @DBRef(lazy = true)
    private StudentEntity entity;

    protected DocumentEntity() {}

}
