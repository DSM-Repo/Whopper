package com.dsm.repo.internal.data.repository.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@Document(collection = "notice_repo")
public class NoticeEntity {
    @Id
    private String id;
    private String title;
    private String content;
    private String writerName;
    private LocalDateTime createdAt;
    private Boolean checked;

    protected NoticeEntity() {}
}
