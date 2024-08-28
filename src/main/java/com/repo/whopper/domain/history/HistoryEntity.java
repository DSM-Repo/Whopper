package com.repo.whopper.domain.history;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Document("history_repo")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class HistoryEntity {
    @Id
    private String id;
    private String date;
    private String content;

    protected HistoryEntity() {}
}
