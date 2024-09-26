package com.repo.whopper.interfaces.notice;

import com.repo.whopper.application.notice.usecase.FetchNoticeUseCase;
import com.repo.whopper.common.http.dto.DataResponseInfo;
import com.repo.whopper.common.swagger.notice.FetchNoticeApiDocumentation;
import com.repo.whopper.interfaces.notice.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class FetchNoticeController implements FetchNoticeApiDocumentation {
    private final FetchNoticeUseCase fetchNoticeUseCase;

    @GetMapping
    public DataResponseInfo<NoticeResponse> fetchNotice() {
        return fetchNoticeUseCase.fetchNotice();
    }
}
