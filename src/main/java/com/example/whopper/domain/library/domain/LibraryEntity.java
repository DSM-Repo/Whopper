package com.example.whopper.domain.library.domain;

import com.example.whopper.domain.library.domain.type.AccessRight;
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
    private Integer year;
    private Integer grade;
    private String filePath;
    private LocalDateTime createAt;
    private AccessRight accessRight;
    private List<DocumentIndex> index;

    public Integer getGeneration() {
        return this.year - this.grade - 2013;
    }

    public void changeAccessRight(AccessRight accessRight) {
        this.accessRight = accessRight;
    }
}