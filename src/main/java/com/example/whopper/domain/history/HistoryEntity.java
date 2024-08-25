package com.example.whopper.domain.history;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("history_repo")
@Getter(value = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class HistoryEntity {
    @Id
    private String id;

    private String date;

    private String content;

    protected HistoryEntity() {}
}
