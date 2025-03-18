package com.dsm.repo.internal.core.usecase.notice;

import com.dsm.repo.external.web.rest.common.DataResponseInfo;
import com.dsm.repo.external.web.rest.notice.dto.response.NoticeResponse;

public interface FetchNoticeUseCase {
    DataResponseInfo<NoticeResponse> fetchNotice();
}
