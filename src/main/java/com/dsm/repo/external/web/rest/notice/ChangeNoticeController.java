package com.dsm.repo.external.web.rest.notice;

import com.dsm.repo.internal.core.usecase.notice.ChangeNoticeUseCase;
import com.dsm.repo.external.security.auth.annotation.OnlyTeacher;
import com.dsm.repo.external.web.documentation.notice.ChangeNoticeApiDocumentation;
import com.dsm.repo.external.web.rest.notice.dto.request.NoticeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
class ChangeNoticeController implements ChangeNoticeApiDocumentation {

    private final ChangeNoticeUseCase changeNoticeUseCase;

    @PostMapping("/{noticeId}")
    public void checkNotice(@PathVariable String noticeId) {
        changeNoticeUseCase.checkNotice(noticeId);
    }

    @OnlyTeacher
    @PatchMapping("/{noticeId}")
    public void editNotice(
            @PathVariable String noticeId,
            @RequestBody NoticeRequest request
    ) {
        changeNoticeUseCase.editNotice(noticeId, request.title(), request.content());
    }

}
