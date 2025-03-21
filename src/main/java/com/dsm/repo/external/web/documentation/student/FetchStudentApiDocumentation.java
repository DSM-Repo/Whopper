package com.dsm.repo.external.web.documentation.student;

import com.dsm.repo.external.exception.error.ErrorResponse;
import com.dsm.repo.external.web.rest.student.dto.CurrentStudentInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

@Tag(name = "student", description = "학생 조회 API")
public interface FetchStudentApiDocumentation {
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK, 성공!",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = CurrentStudentInfoResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "학생을 찾지 못했습니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    @Operation(
            summary = "현재 로그인된 학생 정보 조회 API",
            description = "클라이언트 요청 authorization 헤더의 토큰을 사용하여 id를 추출하고, 학생을 검색합니다."
    )
    CurrentStudentInfoResponse fetchCurrentUserInfoResponse();
}
