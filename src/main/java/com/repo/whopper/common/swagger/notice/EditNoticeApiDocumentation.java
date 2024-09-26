package com.repo.whopper.common.swagger.notice;

import com.repo.whopper.interfaces.notice.dto.request.NoticeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Edit_notice", description = "공지 수정 API")
public interface EditNoticeApiDocumentation {
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK, 성공!"
            )
    })
    @Operation(
            summary = "공지 수정 API",
            description = "클라이언트에서 받은 id로 공지를 검색하고, 검색한 공지의 제목과 내용을 수정합니다."
    )
    void editNotice(String noticeId, NoticeRequest request);
}
