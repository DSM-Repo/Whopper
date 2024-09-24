package com.repo.whopper.common.swagger.notice;

import com.repo.whopper.common.error.ErrorResponse;
import com.repo.whopper.common.http.dto.DataResponseInfo;
import com.repo.whopper.interfaces.notice.dto.response.NoticeDetailResponse;
import com.repo.whopper.interfaces.notice.dto.response.NoticeResponse;
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
                                    implementation = NoticeDetailResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "요청하신 공지를 찾지 못했습니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    @Operation(
            summary = "공지 상세 조회 API",
            description = "클라이언트에서 받은 id로 공지를 검색합니다."
    )
    NoticeDetailResponse fetchNoticeDetailResponse(String noticeId);
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
