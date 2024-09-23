package com.repo.whopper.interfaces.notice;

import com.repo.whopper.application.notice.usecase.FetchNoticeUseCase;
import com.repo.whopper.common.http.response.DataResponseInfo;
import com.repo.whopper.interfaces.notice.dto.response.NoticeDetailResponse;
import com.repo.whopper.interfaces.notice.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class FetchNoticeController {
    private final FetchNoticeUseCase fetchNoticeUseCase;

    @GetMapping("/{noticeId}")
    public NoticeDetailResponse fetchNoticeDetailResponse(@PathVariable String noticeId) {
        return fetchNoticeUseCase.fetchNoticeDetailResponse(noticeId);
    }

    @GetMapping
    public DataResponseInfo<NoticeResponse> fetchNotice() {
        return fetchNoticeUseCase.fetchNotice();
    }
}
