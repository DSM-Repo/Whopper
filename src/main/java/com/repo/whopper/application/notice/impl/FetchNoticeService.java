package com.repo.whopper.application.notice.impl;

import com.repo.whopper.application.notice.usecase.FetchNoticeUseCase;
import com.repo.whopper.common.exception.notice.NoticeNotFoundException;
import com.repo.whopper.common.http.dto.DataResponseInfo;
import com.repo.whopper.domain.notice.NoticeModel;
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
    public NoticeDetailResponse fetchNoticeDetailResponse(String noticeId){
        NoticeModel notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        var newNotice = notice.checkNotice();
        noticeRepository.save(newNotice);

        return new NoticeDetailResponse(notice);
    }

    @Override
    @Transactional(readOnly = true)
    public DataResponseInfo<NoticeResponse> fetchNotice() {
        var notice = noticeRepository.findAll();

        return DataResponseInfo.of(notice
                .map(NoticeResponse::fromModel)
                .toList());
    }
}
