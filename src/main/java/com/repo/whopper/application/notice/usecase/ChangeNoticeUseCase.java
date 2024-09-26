package com.repo.whopper.application.notice.usecase;

public interface ChangeNoticeUseCase {
    void checkNotice(String noticeId);
    void editNotice(String noticeId, String title, String content);
}
