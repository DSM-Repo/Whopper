package com.repo.whopper.domain.history;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Document("history_repo")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class HistoryEntity { // TODO: 3/18/25 복구 가능 PDF에 내용 있음
    @Id
    private String id;
    private String date;
    private String content;

    protected HistoryEntity() {}
}
