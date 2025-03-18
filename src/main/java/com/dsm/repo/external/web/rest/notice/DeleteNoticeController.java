package com.dsm.repo.external.web.rest.notice;

import com.dsm.repo.internal.core.usecase.notice.DeleteNoticeUseCase;
import com.dsm.repo.external.security.auth.annotation.OnlyTeacher;
import com.dsm.repo.external.web.documentation.notice.DeleteNoticeApiDocumentation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
class DeleteNoticeController implements DeleteNoticeApiDocumentation {

    private final DeleteNoticeUseCase deleteNoticeUseCase;

    @OnlyTeacher
    @DeleteMapping("/{noticeId}")
    public void deleteNotice(@PathVariable String noticeId) {
        deleteNoticeUseCase.deleteNotice(noticeId);
    }

}
