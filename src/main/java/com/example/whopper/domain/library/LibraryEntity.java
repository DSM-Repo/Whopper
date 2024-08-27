package com.example.whopper.domain.library;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Document(collection = "library_repo")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LibraryEntity {
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