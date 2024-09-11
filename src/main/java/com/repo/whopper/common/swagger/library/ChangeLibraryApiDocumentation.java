package com.repo.whopper.common.swagger.library;

import com.repo.whopper.common.error.ErrorResponse;
import com.repo.whopper.interfaces.library.dto.LibraryElementDto;
import com.repo.whopper.interfaces.student.dto.CurrentStudentInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

@Tag(name = "library", description = "도서관 API")
public interface ChangeLibraryApiDocumentation {
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
                    description = "요청하신 라이브러리를 찾지 못했습니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    @Operation(
            summary = "도서관 문서 접근권한 변경 API",
            description = "클라이언트에서 받은 id로 문서를 검색하고 문서의 접근권환을 변경합니다."
    )
    void changeLibraryAccessRight(String libraryId, LibraryElementDto.AccessRight accessRight);
}
