package com.dsm.repo.external.web.rest.notice;

import com.dsm.repo.internal.core.usecase.notice.WriteNoticeUseCase;
import com.dsm.repo.external.security.auth.annotation.OnlyTeacher;
import com.dsm.repo.external.web.documentation.notice.WriteNoticeApiDocumentation;
import com.dsm.repo.external.web.rest.notice.dto.request.NoticeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
class WriteNoticeController implements WriteNoticeApiDocumentation {
    private final WriteNoticeUseCase writeNoticeUseCase;

    @OnlyTeacher
    @PostMapping
    public void writeNotice(@RequestBody NoticeRequest request) {
        writeNoticeUseCase.writeNotice(request.title(), request.content());
    }

}
