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

@Tag(name = "Save_library", description = "도서관 저장 API")
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
            summary = "기수별 도서관 문서 저장 API",
            description = "학년과 pdf 파일, 문서의 index를 받아서 library의 형태로 저장합니다."
    )
    void saveLibrary(Integer grade, MultipartFile pdfPart, ResumeIndexRequest indexPart);
}
