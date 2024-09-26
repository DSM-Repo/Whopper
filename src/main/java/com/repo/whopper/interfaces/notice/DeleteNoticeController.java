package com.repo.whopper.interfaces.notice;

import com.repo.whopper.application.notice.usecase.DeleteNoticeUseCase;
import com.repo.whopper.common.annotation.OnlyTeacher;
import com.repo.whopper.common.swagger.notice.DeleteNoticeApiDocumentation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class DeleteNoticeController implements DeleteNoticeApiDocumentation {
    private final DeleteNoticeUseCase deleteNoticeUseCase;

    @OnlyTeacher
    @DeleteMapping("/{noticeId}")
    public void deleteNotice(@PathVariable String noticeId) {
        deleteNoticeUseCase.deleteNotice(noticeId);
    }
}
