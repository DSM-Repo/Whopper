package com.dsm.repo.external.web.documentation.notice;

import com.dsm.repo.external.web.rest.notice.dto.request.NoticeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Write_notice", description = "공지 작성 API")
public interface WriteNoticeApiDocumentation {
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK, 성공!"
            )
    })
    @Operation(
            summary = "공지 작성 API",
            description = "제목과 내용을 받아서 데이터베이스에 저장합니다."
    )
    void writeNotice(NoticeRequest request);
}
