package com.example.whopper.domain.library;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Document(collection = "library_repo")
class LibraryEntity {
    @Id
    private String id;
    private Integer year;
    private Integer grade;
    private String filePath;
    private LocalDateTime createAt;
    private AccessRight accessRight;
    private List<ResumeIndex> index;

    record ResumeIndex(
            String name,
            String major,
            Integer studentNumber,
            Integer pageNumber
    ) {
    }

    enum AccessRight {
        PUBLIC,
        PRIVATE
    }

    protected LibraryEntity() {}
}