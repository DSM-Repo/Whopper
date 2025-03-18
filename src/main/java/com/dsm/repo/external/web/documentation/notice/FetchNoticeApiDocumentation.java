package com.dsm.repo.external.web.documentation.notice;

import com.dsm.repo.external.web.rest.common.DataResponseInfo;
import com.dsm.repo.external.web.rest.notice.dto.response.NoticeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

@Tag(name = "Fetch_notice", description = "공지 조회 API")
public interface FetchNoticeApiDocumentation {
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK, 성공!",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = NoticeResponse.class
                            )
                    )
            )
    })
    @Operation(
            summary = "공지 목록 조회 API",
            description = "전체 공지 목록을 검색합니다."
    )
    DataResponseInfo<NoticeResponse> fetchNotice();
}
