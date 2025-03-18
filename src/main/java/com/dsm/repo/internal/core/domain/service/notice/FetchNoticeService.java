package com.dsm.repo.internal.core.domain.service.notice;

import com.dsm.repo.internal.core.usecase.notice.FetchNoticeUseCase;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;
import com.dsm.repo.internal.data.repository.notice.NoticeRepository;
import com.dsm.repo.external.web.rest.notice.dto.response.NoticeResponse;
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
        var notice = noticeRepository.findAll();

        return DataResponseInfo.of(notice
                .map(NoticeResponse::fromModel)
                .toList());
    }
}
