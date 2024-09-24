package com.repo.whopper.application.notice.usecase;

import com.repo.whopper.common.http.dto.DataResponseInfo;
import com.repo.whopper.interfaces.notice.dto.response.NoticeDetailResponse;
import com.repo.whopper.interfaces.notice.dto.response.NoticeResponse;

public interface FetchNoticeUseCase {
    NoticeDetailResponse fetchNoticeDetailResponse(String noticeId);
    DataResponseInfo<NoticeResponse> fetchNotice();
}
