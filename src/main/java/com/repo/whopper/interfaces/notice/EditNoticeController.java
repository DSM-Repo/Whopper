package com.repo.whopper.interfaces.notice;

import com.repo.whopper.application.notice.usecase.EditNoticeUseCase;
import com.repo.whopper.common.annotation.OnlyTeacher;
import com.repo.whopper.interfaces.notice.dto.request.NoticeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class EditNoticeController {
    private final EditNoticeUseCase editNoticeUseCase;

    @OnlyTeacher
    @PatchMapping("/{noticeId}")
    public void editNotice(@PathVariable String noticeId,  @RequestBody NoticeRequest request) {
        editNoticeUseCase.editNotice(noticeId, request.title(), request.content());
    }
}
