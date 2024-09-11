package com.repo.whopper.common.swagger.library;

import com.repo.whopper.interfaces.library.dto.request.ResumeIndexRequest;
import com.repo.whopper.interfaces.student.dto.CurrentStudentInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "library", description = "도서관 문서 저장 API")
public interface SaveLibraryApiDocumentation {
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
            )
    })
    @Operation(
            summary = "현재 로그인된 학생 정보 조회 API",
            description = "클라이언트 요청 authorization 헤더의 토큰을 사용하여 id를 추출하고, 학생을 검색합니다.",
            parameters = {

            }
    )
    void saveLibrary(Integer grade, MultipartFile pdfPart, ResumeIndexRequest indexPart);
}
