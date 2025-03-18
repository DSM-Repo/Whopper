package com.dsm.repo.internal.core.usecase.notice;

public interface ChangeNoticeUseCase {
    void checkNotice(String noticeId);
    void editNotice(String noticeId, String title, String content);
}
