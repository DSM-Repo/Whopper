package com.repo.whopper.interfaces.notice;

import com.repo.whopper.application.notice.usecase.WriteNoticeUseCase;
import com.repo.whopper.common.annotation.OnlyTeacher;
import com.repo.whopper.common.swagger.notice.WriteNoticeApiDocumentation;
import com.repo.whopper.interfaces.notice.dto.request.NoticeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class WriteNoticeController implements WriteNoticeApiDocumentation {
    private final WriteNoticeUseCase writeNoticeUseCase;

    @OnlyTeacher
    @PostMapping
    public void writeNotice(@RequestBody NoticeRequest request) {
        writeNoticeUseCase.writeNotice(request.title(), request.content());
    }
}
