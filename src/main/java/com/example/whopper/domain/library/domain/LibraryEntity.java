package com.example.whopper.domain.library.domain;

import com.example.whopper.domain.library.domain.type.AccessRight;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Document(collection = "library_repo")
public class LibraryEntity {
    @Id
    private String id;
    private final Integer year;
    private final Integer grade;
    private final String filePath;
    private final LocalDateTime createAt;
    private AccessRight accessRight;
    private final List<DocumentIndex> index;

    @Builder
    public LibraryEntity(Integer year, Integer grade, String filePath, LocalDateTime createAt,
                         AccessRight accessRight, List<DocumentIndex> index) {
        this.year = year;
        this.grade = grade;
        this.filePath = filePath;
        this.createAt = createAt;
        this.accessRight = accessRight;
        this.index = index;
    }

    public Integer getGeneration() {
        return this.year - this.grade - 2013;
    }

    public void changeAccessRight(AccessRight accessRight) {
        this.accessRight = accessRight;
    }
}