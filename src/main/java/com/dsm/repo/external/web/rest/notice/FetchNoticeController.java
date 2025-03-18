package com.dsm.repo.external.web.rest.notice;

import com.dsm.repo.internal.core.usecase.notice.FetchNoticeUseCase;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;
import com.dsm.repo.external.web.documentation.notice.FetchNoticeApiDocumentation;
import com.dsm.repo.external.web.rest.notice.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
class FetchNoticeController implements FetchNoticeApiDocumentation {
    private final FetchNoticeUseCase fetchNoticeUseCase;

    @GetMapping
    public DataResponseInfo<NoticeResponse> fetchNotice() {
        return fetchNoticeUseCase.fetchNotice();
    }

}
