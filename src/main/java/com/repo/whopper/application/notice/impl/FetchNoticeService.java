package com.repo.whopper.application.notice.impl;

import com.repo.whopper.application.notice.usecase.FetchNoticeUseCase;
import com.repo.whopper.common.http.response.DataResponseInfo;
import com.repo.whopper.domain.notice.NoticeRepository;
import com.repo.whopper.interfaces.notice.dto.response.NoticeDetailResponse;
import com.repo.whopper.interfaces.notice.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FetchNoticeService implements FetchNoticeUseCase {
    private final NoticeRepository noticeRepository;

    @Override
    @Transactional(readOnly = true)
    public DataResponseInfo<NoticeResponse> fetchNotice() {
        return noticeRepository.
    }

    @Override
    @Transactional(readOnly = true)
    public NoticeDetailResponse fetchNoticeDetailResponse(String noticeId){

    }
}
