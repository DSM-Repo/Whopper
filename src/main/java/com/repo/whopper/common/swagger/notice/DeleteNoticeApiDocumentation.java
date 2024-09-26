package com.repo.whopper.common.swagger.notice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Delete_notice", description = "공지 삭제 API")
public interface DeleteNoticeApiDocumentation {
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK, 성공!"
            )
    })
    @Operation(
            summary = "공지 삭제 API",
            description = "클라이언트에서 받은 id로 공지를 삭제합니다."
    )
    void deleteNotice(String noticeId);
}
